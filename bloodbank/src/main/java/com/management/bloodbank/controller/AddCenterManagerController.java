package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.service.CentersService;
import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AddCenterManagerController {

    private final UserService userService;
    private final CentersService centersService;

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("centers", centersService.findAll());
        return "admin/users";
    }

    @PostMapping("/admin/users/{id}/assignCenterManager")
    public String assignCenterManager(@PathVariable Long id,
            @RequestParam Long centerId, RedirectAttributes redirectAt) {

        userService.assignCenterManager(id, centerId);
        redirectAt.addFlashAttribute("successMessage", "User promoted to Center Manager.");
        return "redirect:/admin/users";
    }
}