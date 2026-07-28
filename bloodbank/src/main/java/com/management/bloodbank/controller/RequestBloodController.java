package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestBloodController {
	@GetMapping("/request-blood")
	public String getBloodRequest() {
		return "request";
	}
	
	@GetMapping("/request/list")
	@ResponseBody
	public String getBloodRequestList() {
		return "Blood Request List";
	}
}
