package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.bloodbank.model.Centers;

@Controller
public class CenterController {

	@GetMapping("/centers")
	public String getCenters(Model model) {
		model.addAttribute("centers", new Centers());
		return "centers";
	}
	
	@GetMapping("/admin/centers")
	public String getAdminCenters(Model model) {
		model.addAttribute("centers", new Centers());
		return "centers";
	}
}
