package com.management.bloodbank.controller;

import com.management.bloodbank.model.Center;
import com.management.bloodbank.service.CenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CenterController {

	private final CenterService centerService;

	@GetMapping("/centres")
	public String listCentres(Model model) {
		model.addAttribute("centres", centerService.findAll());
		model.addAttribute("newCentre", new Center());
		return "centres";
	}

	@PostMapping("/admin/centres")
	public String addCentre(@Valid @ModelAttribute Center newCentre, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("centres", centerService.findAll());
			model.addAttribute("centreError", "Please fill in all required fields.");
			return "centres";
		}
		centerService.save(newCentre);
		return "redirect:/centres";
	}
}