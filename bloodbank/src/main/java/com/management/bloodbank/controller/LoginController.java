package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	//  @PostMapping("/login") is handled using spring securtiy "CustomUserDetailsService"
}
