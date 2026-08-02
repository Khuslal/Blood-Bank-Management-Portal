package com.management.bloodbank.controller;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.User;
import com.management.bloodbank.service.AppointmentService;
import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @GetMapping("/donor/appointment")
    public String getAppointments() {
        return "appointment";
    }

    @PostMapping("/donor/appointment")
    public String postAppointment(@ModelAttribute Appointment appointment,
                                   @AuthenticationPrincipal UserDetails principal,
                                   RedirectAttributes redirectAt) {

        User loggedInUser = userService.findByEmail(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("Logged in user not found"));


        redirectAt.addFlashAttribute("success-message", "Appointment booked successfully!");
        if (appointmentService.existsByUser(loggedInUser)) {

            redirectAt.addFlashAttribute("error-message",
                    "Booking can't be duplicated.");

            return "redirect:/donor/appointment";
        }
        
        appointment.setUser(loggedInUser);
        appointmentService.save(appointment);
        redirectAt.addFlashAttribute("success-message",
                "Appointment booked successfully!");
        
        return "redirect:/dashboard";
    }
    
    @GetMapping("/admin/appointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "view-appointments";
    }
}