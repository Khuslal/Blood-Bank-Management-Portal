package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.bloodbank.model.RequestStatus;
import com.management.bloodbank.service.BloodRequestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminRequestController {

	private final BloodRequestService bloodRequestService;

	@PostMapping("/admin/requests/{id}/status")
	public String updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
		bloodRequestService.updateStatus(id, status);
		return "redirect:/dashboard";
	}
}