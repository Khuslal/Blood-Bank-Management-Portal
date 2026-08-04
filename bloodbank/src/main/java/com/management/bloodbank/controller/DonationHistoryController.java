package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DonationHistoryController {

//	private final UserService userService;
	
	@GetMapping("/donation-history")
	public String getDonationHistory() {
		
		return "donationHistory";
	}
}
