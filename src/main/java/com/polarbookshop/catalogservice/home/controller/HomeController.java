package com.polarbookshop.catalogservice.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/{msg}")
    public String getGreeting(@PathVariable String msg) {
        return "<h1 style=\"color: purple;\">Welcome to the book catalog!" + "\n your message: " + msg + "</h1>";
    }
}
