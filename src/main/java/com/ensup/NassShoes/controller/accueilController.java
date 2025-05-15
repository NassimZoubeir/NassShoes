package com.ensup.NassShoes.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class accueilController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String accueil(Model model) {
        model.addAttribute("appName", appName);
        return "accueil";
    }
    @GetMapping("/accueil")
    public String accueil1(Model model) {
        model.addAttribute("appName", appName);
        return "accueil";
    }
    @GetMapping("/catalogue")
	   public String catalogue(Model model) {
		model.addAttribute("appName", appName);
	     return "catalogue";
	    }
}
