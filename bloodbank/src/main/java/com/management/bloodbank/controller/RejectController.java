package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.service.AppointmentService;

@Controller
public class RejectController {
	
	private AppointmentService appointmentService;
	
	@GetMapping("/appointment/reject/{id}")
	public String rejectAppointment(@PathVariable Long id,
	                                RedirectAttributes redirectAttributes) {

	    Appointment appointment = appointmentService.findById(id).orElseThrow();

	    appointment.setStatus(AppointmentStatus.REJECTED);

	    appointmentService.save(appointment);

	    redirectAttributes.addFlashAttribute("success",
	            "Appointment rejected successfully.");

	    return "redirect:/view-appointments";
	}
}
