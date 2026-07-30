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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/admin/centres/{id}/edit")
	public String editCentreForm(@PathVariable Long id, Model model) {
	    model.addAttribute("centre", centerService.findById(id));
	    return "centre-edit";
	}

	@PostMapping("/admin/centres/{id}")
	public String updateCentre(@PathVariable Long id,
	                            @Valid @ModelAttribute("centre") Center centre,
	                            BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "centre-edit";
	    }
	    // ensures hibernate updates instead of insert when id has value
	    centre.setId(id);
	    centerService.save(centre);
	    return "redirect:/centres";
	}
}