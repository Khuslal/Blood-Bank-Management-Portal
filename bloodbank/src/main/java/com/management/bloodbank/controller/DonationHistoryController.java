package com.management.bloodbank.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;
import com.management.bloodbank.service.DonationHistoryService;
import com.management.bloodbank.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DonationHistoryController {
	private final DonationHistoryService donationHistoryService;
	private final UserService userService;

	@GetMapping("/donation-history")
	public String getDonationHistory(Authentication authentication, Model model) {
		User currentUser = userService.findByEmail(authentication.getName())
				.orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));

		if (currentUser.getRole() == UserRole.ADMIN) {
			// Donation record of all the donors to access only by the admin
			model.addAttribute("history", donationHistoryService.findAll());
			model.addAttribute("isAdmin", true);
		} else if (currentUser.getRole() == UserRole.CENTER_MANAGER) {
			// Donation History of the particular center to shows records to the authorized
			// CENTER_MANAGER
			Long centerId = currentUser.getAssignedCenter() != null ? currentUser.getAssignedCenter().getId() : null;
			model.addAttribute("history",
					centerId != null ? donationHistoryService.findByCenterId(centerId) : java.util.List.of());
			model.addAttribute("isAdmin", true);
		} else {
			// Donation record of an authorized donor
			model.addAttribute("history", donationHistoryService.findByUserId(currentUser.getId()));
			model.addAttribute("isAdmin", false);
		}

		return "donationHistory";
	}
}