package com.example.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.billing.model.Password;
import com.example.billing.service.PasswordService;

@Controller
public class PasswordSetupController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/setup-password/{employeeId}")
public String setupPassword(@PathVariable String employeeId) {
    if (!employeeId.matches("\\d+")) {        
        return "error-page";
    }
    Long id = Long.parseLong(employeeId);
    return "password-setup";
}


    @PostMapping("/api/setup-password/{employeeId}")
    public ResponseEntity<String> setupPassword(@RequestBody String password, @PathVariable Long employeeId) {
        passwordService.setupPassword(password, employeeId);
        return ResponseEntity.ok("Password successfully set up for employee ID: " + employeeId);
    }
}
