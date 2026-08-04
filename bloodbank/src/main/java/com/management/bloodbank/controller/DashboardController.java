package com.management.bloodbank.controller;

import java.security.Principal;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.bloodbank.model.User;
import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DashboardController {

	private final UserService userService;

	@GetMapping("/dashboard")
	public String getDashboard(Principal principal, Model model) {
		String email = principal.getName();
		User loggedInUser = userService.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found in database!"));
		model.addAttribute("user", loggedInUser);
		return "dashboard";
	}
}
