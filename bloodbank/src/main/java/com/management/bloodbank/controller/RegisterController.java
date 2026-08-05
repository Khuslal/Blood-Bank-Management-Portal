package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.User;
import com.management.bloodbank.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {
	
	private final UserService userService;
	
	@GetMapping("/register")
	public String getRegister(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("bloodGroups", BloodGroup.values());
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@Valid @ModelAttribute User user, 
			BindingResult bindingResult, 
			Model model, RedirectAttributes redirectAt) {
		
		if(bindingResult.hasErrors()) {
			 model.addAttribute("bloodGroups", BloodGroup.values());
	            return "register";
		}
		
		try{
			userService.registerNewUser(user);
			 redirectAt.addFlashAttribute("successMessage", "Registration successful! Please login.");
			 return "redirect:/login";
		} catch(IllegalArgumentException e){
			model.addAttribute("emailError", e.getMessage());
			model.addAttribute("bloodGroups", BloodGroup.values());
			return "register";
		}
	}
}
