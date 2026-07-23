package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("/dashboard")
	public String getDashboard() {
		return "dashboard";
	}
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
}
