package com.management.bloodbank.ApiController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControllerApi {
	
	
	@GetMapping({"/api","/api/"})
	public String getApiHome() {
		return "Home Page";
	}
}
