package com.management.bloodbank.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.service.BloodRequestService;
import com.management.bloodbank.service.CentersService;
import com.management.bloodbank.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BloodRequestController {
	
	private final BloodRequestService bloodRequestService;
	private final CentersService centersService;
	private final FileStorageService fileStorageService;
	
	@GetMapping("/request")
	public String getBloodRequest(BloodRequest bloodRequest, Model model) {
		model.addAttribute("bloodRequest", bloodRequest);
		model.addAttribute("bloodGroups", BloodGroup.values()); 
		model.addAttribute("centers", centersService.findAll());
		return "bloodRequest";
	}
	
	@PostMapping("/request")
	public String postBloodRequest(@ModelAttribute BloodRequest bloodRequest,
	                                @RequestParam("validDocs") MultipartFile validDocs,
	                                @RequestParam(value = "centerId", required = false) Long centerId,
	                                Model model,
	                                RedirectAttributes redirectAttributes) throws IOException {

	    if (validDocs.isEmpty()) {
	        model.addAttribute("bloodGroups", BloodGroup.values());
	        model.addAttribute("centers", centersService.findAll());
	        model.addAttribute("centerError", "Please upload a supporting document.");
	        return "bloodRequest";
	    }

	    String storedFileName = fileStorageService.storeFile(validDocs);

	    bloodRequest.setDocumentName(validDocs.getOriginalFilename());
	    bloodRequest.setDocumentPath(storedFileName);
	    bloodRequest.setDocumentType(validDocs.getContentType());

	    if (centerId != null) {
	        Centers center = centersService.findById(centerId)
	                .orElseThrow(() -> new RuntimeException("Selected center not found"));
	        bloodRequest.setCenters(center);
	    }

	    bloodRequestService.registerNewBloodRequest(bloodRequest);

	    redirectAttributes.addFlashAttribute("submitted", true);
	    return "redirect:/request";
	}
}
