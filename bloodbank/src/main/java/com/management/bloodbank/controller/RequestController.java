package com.management.bloodbank.controller;

import com.management.bloodbank.model.*;
import com.management.bloodbank.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private final BloodRequestService bloodRequestService;
    private final CenterService centerService;
    private final FileStorageService fileStorageService;

    @GetMapping("/request")
    public String requestForm(Model model) {
        model.addAttribute("bloodRequest", new Request());
        model.addAttribute("bloodGroups", BloodGroup.values());
        model.addAttribute("centres", centerService.findAll());
        return "request";
    }

    @PostMapping("/request")
    public String postRequest(@Valid @ModelAttribute Request bloodRequest,
                                 BindingResult bindingResult,
                                 @RequestParam(required = false) Long centerId,
                                 @RequestParam(required = false) MultipartFile prescriptionFile,
                                 @RequestParam(required = false) MultipartFile hospitalRequisitionFile,
                                 @RequestParam(required = false) MultipartFile patientSampleFile,
                                 @RequestParam(required = false) MultipartFile donorExchangeFile,
                                 Model model) {

        boolean hasAnyDocument = hasContent(prescriptionFile) || hasContent(hospitalRequisitionFile)
                || hasContent(patientSampleFile) || hasContent(donorExchangeFile);

        if (!hasAnyDocument) {
            bindingResult.reject("document.missing",
                "Please upload at least one supporting document: prescription, hospital requisition, patient sample, or donor exchange proof.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("bloodGroups", BloodGroup.values());
            model.addAttribute("centres", centerService.findAll());
            return "request";
        }

        if (centerId != null) {
            bloodRequest.setCenter(centerService.findById(centerId));
        }

        bloodRequest.setPrescriptionFile(fileStorageService.store(prescriptionFile));
        bloodRequest.setHospitalRequisitionFile(fileStorageService.store(hospitalRequisitionFile));
        bloodRequest.setPatientSampleFile(fileStorageService.store(patientSampleFile));
        bloodRequest.setDonorExchangeFile(fileStorageService.store(donorExchangeFile));

        bloodRequestService.submitRequest(bloodRequest);

        model.addAttribute("submitted", true);
        model.addAttribute("bloodRequest", new Request());
        model.addAttribute("bloodGroups", BloodGroup.values());
        model.addAttribute("centres", centerService.findAll());
        return "request";
    }

    private boolean hasContent(MultipartFile file) {
        return file != null && !file.isEmpty();
    }
}