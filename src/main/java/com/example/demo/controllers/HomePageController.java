package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class HomePageController {
    @GetMapping("/")
    public String indexPage(Model model) {
        System.out.println("return homepage view");
        return "index";
    }
}
