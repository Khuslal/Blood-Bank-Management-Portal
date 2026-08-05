package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentController {

	@GetMapping("/admin/appointments")
	public String getAppointment() {
		return "view-appointments";
	}
}
