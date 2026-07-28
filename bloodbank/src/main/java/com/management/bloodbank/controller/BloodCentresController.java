package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BloodCentresController {
	@GetMapping("/blood-centres")
	public String getBloodCentres() {
		return "centres";
	}
}
