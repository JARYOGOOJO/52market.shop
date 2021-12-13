package com.sparta.cucumber.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthCheckController {
    @Operation(description = "서버 상태 체크", method = "GET")
    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        String result = "Hello World!!";
        return ResponseEntity.ok(result);
    }
}
