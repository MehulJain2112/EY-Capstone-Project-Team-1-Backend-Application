package com.ey.ecalendar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Map<String, String> response = new HashMap<>();
        if ("manager".equals(username) && "password".equals(password)) {
            response.put("status", "success");
            response.put("message", "Login successful");
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid credentials");
        }

        return response;
    }
}