package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonationController {
	
	@GetMapping("/donation/history")
	public String getDonationHistory() {
		return "donationHistory";
	}
}
