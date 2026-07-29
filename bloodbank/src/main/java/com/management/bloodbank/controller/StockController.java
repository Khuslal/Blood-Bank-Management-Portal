package com.management.bloodbank.controller;

import com.management.bloodbank.service.BloodStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StockController {

    private final BloodStockService bloodStockService;

    @GetMapping("/stock")
    public String stock(Model model) {
        model.addAttribute("stockList", bloodStockService.findAll());
        return "stock";
    }
}