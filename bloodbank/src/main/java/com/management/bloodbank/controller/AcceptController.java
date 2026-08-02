package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AcceptController {
	
	private AppointmentService appointmentService;
	
	@GetMapping("/appointment/accept/{id}")
	public String acceptAppointment(@PathVariable Long id,
	                                RedirectAttributes redirectAttributes) {

	    Appointment appointment = appointmentService.findById(id).orElseThrow();

	    appointment.setStatus(AppointmentStatus.ACCEPTED);

	    appointmentService.save(appointment);

	    redirectAttributes.addFlashAttribute("success",
	            "Appointment accepted successfully.");

	    return "redirect:/view-appointments";
	}
}
