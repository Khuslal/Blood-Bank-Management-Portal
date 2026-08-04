package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;

@Controller
public class BloodRequestController {
	
	@GetMapping("/request")
	public String getBloodRequestController(BloodRequest bloodRequest, Model model) {
		model.addAttribute("bloodRequest", bloodRequest);
		model.addAttribute("bloodGroup", BloodGroup.values());
		model.addAttribute("centers", java.util.List.of());
		return "bloodRequest";
	}
}
