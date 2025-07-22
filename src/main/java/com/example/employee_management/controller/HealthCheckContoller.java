package com.example.employee_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckContoller {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    // Greeting: GET /api/hello/{name}
    @GetMapping("/hello/{name}")
    public ResponseEntity<String> greet(@PathVariable String name) {
        return ResponseEntity.ok("Hello " + name);
    }
}
