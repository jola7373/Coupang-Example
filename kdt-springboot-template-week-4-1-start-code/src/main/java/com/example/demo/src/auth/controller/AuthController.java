package com.example.demo.src.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/")
    public String home() {
        return "hello, home";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, Secured";
    }

}
