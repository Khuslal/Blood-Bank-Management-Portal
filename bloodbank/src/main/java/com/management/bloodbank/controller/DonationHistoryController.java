package com.management.bloodbank.controller;

import com.management.bloodbank.model.*;
import com.management.bloodbank.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/donation-history")
public class DonationHistoryController {

    private final DonationHistoryService donationHistoryService;
    private final DonorService donorService;
    private final CenterService centerService;
    private final UserService userService;

    @GetMapping
    public String list(@AuthenticationPrincipal UserDetails principal, Model model) {
        User user = userService.findByEmail(principal.getUsername()).orElseThrow();

        if (user.getRole() == UserRole.ADMIN) {
            model.addAttribute("history", donationHistoryService.findAll());
            model.addAttribute("centres", centerService.findAll());
            model.addAttribute("isAdmin", true);
        } else {
            Donor donor = donorService.findByUserEmail(user.getEmail()).orElse(null);
            model.addAttribute("history", donor != null
                    ? donationHistoryService.findByDonorId(donor.getId())
                    : java.util.List.of());
            model.addAttribute("isAdmin", false);
        }
        return "donationHistory";
    }

    @PostMapping("/record")
    public String record(@RequestParam Long donorId,
                          @RequestParam Long centerId,
                          @RequestParam BloodGroup bloodGroup,
                          @RequestParam Integer unitsDonated) {
        // Admin-only action to log a completed donation; enforced via SecurityConfig role rules.
        DonationHistory dh = new DonationHistory();
        Donor donor = new Donor();
        donor.setId(donorId);
        dh.setDonor(donorService.findAll().stream()
                .filter(d -> d.getId().equals(donorId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Donor not found")));
        dh.setCenter(centerService.findById(centerId));
        dh.setBloodGroup(bloodGroup);
        dh.setUnitsDonated(unitsDonated);
        donationHistoryService.recordDonation(dh);
        return "redirect:/donation-history";
    }
}