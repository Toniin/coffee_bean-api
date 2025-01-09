package com.coffee_bean.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {
    @PostMapping("/ressource")
    public String ressource(HttpServletRequest request) {
        return "ressource";
    }
}
