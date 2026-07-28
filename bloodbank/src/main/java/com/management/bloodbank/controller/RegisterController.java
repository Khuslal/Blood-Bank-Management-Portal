package com.management.bloodbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.Donor;
import com.management.bloodbank.service.DonorService;

@Controller
public class RegisterController {
	
	@Autowired
	private DonorService donorService;
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	// Donor Controller Method
	@PostMapping("/register/submit")
	public String postRegister(@ModelAttribute Donor donor, RedirectAttributes redirectAttr) {
		donorService.donorSignup(donor);
		redirectAttr.addFlashAttribute("message", "Account created successfully.");
		return "redirect:/dashboard";
	}
}
