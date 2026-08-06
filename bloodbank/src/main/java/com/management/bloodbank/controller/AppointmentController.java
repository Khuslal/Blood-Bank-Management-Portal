package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentController {

	@GetMapping("/admin/appointments")
	public String getAdminAppointments() {
		return "view-appointments";
	}
	
	@GetMapping("/donor/appointment")
	public String getAppointment() {
		return "appointment";
	}
}
