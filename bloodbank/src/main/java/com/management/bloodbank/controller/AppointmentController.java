package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppointmentController {
	
	@GetMapping("/appointments")
	@ResponseBody
	public String getAppointments() {
		return "Get Appointment Here";
	}
}
