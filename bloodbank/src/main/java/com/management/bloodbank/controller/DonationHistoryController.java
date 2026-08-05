package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.bloodbank.service.DonationHistoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DonationHistoryController {

	private final DonationHistoryService donationHistoryService;
	
	@GetMapping("/donation-history")
	public String getDonationHistory(Model model) {
		model.addAttribute("history", donationHistoryService.findAll());
		return "donationHistory";
	}
}
