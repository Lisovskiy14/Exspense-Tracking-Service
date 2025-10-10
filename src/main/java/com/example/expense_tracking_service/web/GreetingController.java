package com.example.expense_tracking_service.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {

    @GetMapping("/healthcheck")
    public ResponseEntity<Object> getHealthcheck() {
        Map<String, String> health = new HashMap<>();
        health.put("date", new Date().toString());
        health.put("status", "ok");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(health);
    }
}
