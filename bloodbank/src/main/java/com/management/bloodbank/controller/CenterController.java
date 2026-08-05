package com.management.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.bloodbank.model.Centers;
import com.management.bloodbank.service.CentersService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CenterController {
    
    private final CentersService centersService;

    @GetMapping("/centers")
    public String getCenters(Model model) {
        List<Centers> centerList = centersService.findAll();
        model.addAttribute("centers", centerList);   // list, for th:each
        model.addAttribute("newCenter", new Centers()); // empty object, for th:object form binding
        return "centers";
    }
    
    @PostMapping("/admin/centers")
    public String postCenters(@ModelAttribute Centers newCenter) {
    	
    	centersService.registerCenters(newCenter);
    	return "redirect:/centers";
    }
}