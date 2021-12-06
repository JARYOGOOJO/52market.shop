package com.sparta.cucumber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        String result = "Hello World!!";
        return ResponseEntity.ok(result);
    }
}
