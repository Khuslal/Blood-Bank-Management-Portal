package com.management.bloodbank.controller;

import com.management.bloodbank.model.*;
import com.management.bloodbank.service.DonorService;
import com.management.bloodbank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
	private final DonorService donorService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("bloodGroups", BloodGroup.values());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			@RequestParam BloodGroup bloodGroup, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("bloodGroups", BloodGroup.values());
			return "register";
		}
		if (userService.emailExists(user.getEmail())) {
			model.addAttribute("bloodGroups", BloodGroup.values());
			model.addAttribute("emailError", "An account with this email already exists.");
			return "register";
		}

		user.setRole(UserRole.DONOR);
		User saved = userService.registerUser(user);

		Donor donor = new Donor();
		donor.setUser(saved);
		donor.setBloodGroup(bloodGroup);
		donorService.registerDonor(donor);

		model.addAttribute("registered", true);
		return "login";
	}
}