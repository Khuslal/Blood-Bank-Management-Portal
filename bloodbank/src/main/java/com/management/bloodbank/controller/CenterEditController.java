package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.Centers;
import com.management.bloodbank.service.CentersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CenterEditController {

	private final CentersService centersService;
	
	@GetMapping("/admin/centers/{id}/edit")
	public String getCenterEdit(@PathVariable("id") Long id, Model model) {
		Centers center = centersService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid center Id : "+id));
		model.addAttribute("center", center);
		return "center-edit";
	}
	
	@PostMapping("/admin/centers/{id}")
	public String postCenterUpdate(@PathVariable("id") Long id, 
	                               @Valid @ModelAttribute("center") Centers center, 
	                               BindingResult bindingResult, 
	                               Model model, 
	                               RedirectAttributes redirectAt) {
	    
	    
	    if (bindingResult.hasErrors()) {
	        return "center-edit";
	    }
	    
	    try {
	        // ID must be assigned before updating
	        center.setId(id); 
	        centersService.updateCenter(center);
	        redirectAt.addFlashAttribute("successMessage", "Center updated successfully!");
	        return "redirect:/centers";
	        
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Failed to update center: " + e.getMessage());
	        return "center-edit";
	    }
	}

}
