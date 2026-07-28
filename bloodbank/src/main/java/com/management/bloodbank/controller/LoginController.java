package com.management.bloodbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.Donor;
import com.management.bloodbank.repository.DonorRepository;
import com.management.bloodbank.service.DonorService;

@Controller
public class LoginController {
	
	@Autowired
	private DonorService donorService;
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping("/login/submit")
	public String postLogin(@ModelAttribute Donor donor, RedirectAttributes redirectAttr) {
		Donor dbDonor = donorService.donorLogin(donor.getUsername(), donor.getPassword());
		if(dbDonor == null) {
			redirectAttr.addFlashAttribute("message", "Invalid username or password.");
			return "redirect:/login";
		}	
		
		return "redirect:/dashboard";
	}
}
