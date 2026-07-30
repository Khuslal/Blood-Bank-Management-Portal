package com.management.bloodbank.controller;

import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;
import com.management.bloodbank.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

	private final UserService userService;
	private final DonorService donorService;
	private final DonationHistoryService donationHistoryService;
	private final BloodRequestService bloodRequestService;
	private final BloodStockService bloodStockService;
	private static final int LOW_STOCK_THRESHOLD = 5;

	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal UserDetails principal, Model model) {
		User user = userService.findByEmail(principal.getUsername())
				.orElseThrow(() -> new IllegalStateException("Logged in user not found"));
		model.addAttribute("user", user);

		if (user.getRole() == UserRole.DONOR) {
			donorService.findByUserEmail(user.getEmail()).ifPresent(donor -> {
				model.addAttribute("donor", donor);
				model.addAttribute("donationCount", donationHistoryService.findByDonorId(donor.getId()).size());
			});
		}

		if (user.getRole() == UserRole.ADMIN) {
			model.addAttribute("allRequests", bloodRequestService.findAll());
			model.addAttribute("stock", bloodStockService.findAll());
			model.addAttribute("lowStock", bloodStockService.lowStock(LOW_STOCK_THRESHOLD));
			model.addAttribute("allDonors", donorService.findAll());
		}
		return "dashboard";
	}
}