package com.management.bloodbank.controller;

import com.management.bloodbank.model.*;
import com.management.bloodbank.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/request")
public class RequestController {

    private final BloodRequestService bloodRequestService;
    private final CenterService centerService;
    private final UserService userService;

    @GetMapping
    public String requestForm(Model model) {
        model.addAttribute("bloodRequest", new BloodRequest());
        model.addAttribute("bloodGroups", BloodGroup.values());
        model.addAttribute("centres", centerService.findAll());
        return "request";
    }

    @PostMapping
    public String submitRequest(@Valid @ModelAttribute("bloodRequest") BloodRequest bloodRequest,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal UserDetails principal,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bloodGroups", BloodGroup.values());
            model.addAttribute("centres", centerService.findAll());
            return "request";
        }

        User requester = userService.findByEmail(principal.getUsername()).orElseThrow();
        bloodRequest.setRequester(requester);
        bloodRequestService.submitRequest(bloodRequest);

        model.addAttribute("submitted", true);
        model.addAttribute("bloodRequest", new BloodRequest());
        model.addAttribute("bloodGroups", BloodGroup.values());
        model.addAttribute("centres", centerService.findAll());
        return "request";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        bloodRequestService.updateStatus(id, status);
        return "redirect:/dashboard";
    }
}