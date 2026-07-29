package com.management.bloodbank.controller;

import com.management.bloodbank.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CenterService centerService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/centres")
    public String centres(Model model) {
        model.addAttribute("centres", centerService.findAll());
        return "centres";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message,
                                 Model model) {
        // Hook this up to an email service or a ContactMessage entity if you want to persist it.
        model.addAttribute("submitted", true);
        return "contact";
    }
}