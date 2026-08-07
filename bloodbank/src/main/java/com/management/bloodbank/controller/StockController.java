package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.management.bloodbank.service.StockService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StockController {

	private final StockService stockService;
	
	@GetMapping("/stock")
	public String getStock(Model model) {
		model.addAttribute("stockList", stockService.findAll());
		return "stock";
	}
}
