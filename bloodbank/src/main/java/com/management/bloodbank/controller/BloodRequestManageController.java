package com.management.bloodbank.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.User;
import com.management.bloodbank.service.BloodRequestService;
import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BloodRequestManageController {

	private final BloodRequestService bloodRequestService;
	private final UserService userService;

	@GetMapping("/admin/requests")
	public String getAdminRequests(Model model) {
		model.addAttribute("requests", bloodRequestService.findAll());
		model.addAttribute("actionPrefix", "/admin/requests");
		return "view-requests";
	}

	@GetMapping("/centerManager/requests")
	public String getManagerRequests(Authentication authentication, Model model) {
		User currentUser = currentUser(authentication);
		List<BloodRequest> requests = currentUser.getAssignedCenter() != null
				? bloodRequestService.findByCenters(currentUser.getAssignedCenter())
				: List.of();
		model.addAttribute("requests", requests);
		model.addAttribute("actionPrefix", "/centerManager/requests");
		return "view-requests";
	}

	@GetMapping("/admin/requests/approve/{id}")
	public String adminApprove(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleApprove(id, authentication, redirectAt, "/admin/requests");
	}

	@GetMapping("/admin/requests/reject/{id}")
	public String adminReject(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleReject(id, authentication, redirectAt, "/admin/requests");
	}

	@GetMapping("/admin/requests/fulfill/{id}")
	public String adminFulfill(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleFulfill(id, authentication, redirectAt, "/admin/requests");
	}

	@GetMapping("/centerManager/requests/approve/{id}")
	public String managerApprove(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleApprove(id, authentication, redirectAt, "/centerManager/requests");
	}

	@GetMapping("/centerManager/requests/reject/{id}")
	public String managerReject(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleReject(id, authentication, redirectAt, "/centerManager/requests");
	}

	@GetMapping("/centerManager/requests/fulfill/{id}")
	public String managerFulfill(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleFulfill(id, authentication, redirectAt, "/centerManager/requests");
	}

	private String handleApprove(Long id, Authentication authentication, RedirectAttributes redirectAt, String base) {
		try {
			bloodRequestService.approve(id, currentUser(authentication));
			redirectAt.addFlashAttribute("success", "Request approved.");
		} catch (IllegalArgumentException e) {
			redirectAt.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:" + base;
	}

	private String handleReject(Long id, Authentication authentication, RedirectAttributes redirectAt, String base) {
		try {
			bloodRequestService.reject(id, currentUser(authentication));
			redirectAt.addFlashAttribute("success", "Request rejected.");
		} catch (IllegalArgumentException e) {
			redirectAt.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:" + base;
	}

	private String handleFulfill(Long id, Authentication authentication, RedirectAttributes redirectAt, String base) {
		try {
			bloodRequestService.fulfill(id, currentUser(authentication));
			redirectAt.addFlashAttribute("success", "Request fulfilled — stock updated.");
		} catch (IllegalArgumentException e) {
			redirectAt.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:" + base;
	}

	private User currentUser(Authentication authentication) {
		return userService.findByEmail(authentication.getName())
				.orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));
	}
}