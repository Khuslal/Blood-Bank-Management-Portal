package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {

	@ResponseBody
	@GetMapping("/logout")
	public String getLogout() {
		return "Logout Page";
	}
}
