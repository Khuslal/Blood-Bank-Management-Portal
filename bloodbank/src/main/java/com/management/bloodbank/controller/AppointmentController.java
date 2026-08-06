package com.management.bloodbank.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.dto.AppointmentForm;
import com.management.bloodbank.model.User;
import com.management.bloodbank.service.AppointmentService;
import com.management.bloodbank.service.CentersService;
import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;
	private final CentersService centersService;
	private final UserService userService;

	// Donor: book appointment @GetMapping

	@GetMapping("/donor/appointment")
	public String getAppointment(Model model) {
		model.addAttribute("allCenters", centersService.findAll());
		model.addAttribute("appointmentForm", new AppointmentForm());
		return "appointment";
	}

	// Donor: book appointment @PostMapping
	@PostMapping("/donor/appointment")
	public String postAppointment(@ModelAttribute AppointmentForm appointmentForm, Authentication authentication,
			Model model, RedirectAttributes redirectAt) {

		User currentUser = currentUser(authentication);

		try {
			appointmentService.bookAppointment(currentUser, appointmentForm.getDonationCenter(),
					appointmentForm.getDonationDate(), appointmentForm.getTimeSlot());
			redirectAt.addFlashAttribute("successMessage", "Appointment booked successfully!");
			return "redirect:/donor/appointment";
		} catch (IllegalArgumentException e) {
			model.addAttribute("allCenters", centersService.findAll());
			model.addAttribute("errorMessage", e.getMessage());
			return "appointment";
		}
	}

	// Donor check his/her appointment status
	@GetMapping("/donor/my-appointments")
	public String getMyAppointments(Authentication authentication, Model model) {
		User currentUser = currentUser(authentication);
		model.addAttribute("appointments", appointmentService.findByUser(currentUser));
		return "my-appointments";
	}

	// Admin: view & manage all appointments

	@GetMapping("/admin/appointments")
	public String getAdminAppointments(Model model) {
		model.addAttribute("appointments", appointmentService.findAll());
		model.addAttribute("actionPrefix", "/admin/appointments");
		return "view-appointments";
	}

	@GetMapping("/admin/appointments/accept/{id}")
	public String adminAccept(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleAccept(id, authentication, redirectAt, "/admin/appointments");
	}

	@GetMapping("/admin/appointments/reject/{id}")
	public String adminReject(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleReject(id, authentication, redirectAt, "/admin/appointments");
	}

	// Center Manager: view & manage own center's appointments

	@GetMapping("/centerManager/appointments")
	public String getManagerAppointments(Authentication authentication, Model model) {
		User currentUser = currentUser(authentication);
		List<?> appointments = currentUser.getAssignedCenter() != null
				? appointmentService.findByCenter(currentUser.getAssignedCenter())
				: List.of();
		model.addAttribute("appointments", appointments);
		model.addAttribute("actionPrefix", "/centerManager/appointments");
		return "view-appointments";
	}

	@GetMapping("/centerManager/appointments/accept/{id}")
	public String managerAccept(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleAccept(id, authentication, redirectAt, "/centerManager/appointments");
	}

	@GetMapping("/centerManager/appointments/reject/{id}")
	public String managerReject(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAt) {
		return handleReject(id, authentication, redirectAt, "/centerManager/appointments");
	}

	// shared helpers to accept the appointments request

	private String handleAccept(Long id, Authentication authentication, RedirectAttributes redirectAt,
			String redirectBase) {
		try {
			appointmentService.accept(id, currentUser(authentication));
			redirectAt.addFlashAttribute("success", "Appointment accepted.");
		} catch (IllegalArgumentException e) {
			redirectAt.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:" + redirectBase;
	}

	// shared helpers to reject the appointments request
	private String handleReject(Long id, Authentication authentication, RedirectAttributes redirectAt,
			String redirectBase) {
		try {
			appointmentService.reject(id, currentUser(authentication));
			redirectAt.addFlashAttribute("success", "Appointment rejected.");
		} catch (IllegalArgumentException e) {
			redirectAt.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:" + redirectBase;
	}

	private User currentUser(Authentication authentication) {
		String email = authentication.getName();
		return userService.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));
	}
}