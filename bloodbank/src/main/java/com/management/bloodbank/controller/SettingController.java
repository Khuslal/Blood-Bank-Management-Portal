package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingController {
	@GetMapping("/settings")
	@ResponseBody
	public String getSetting() {
		return "Your Settings Here!";
	}
}
