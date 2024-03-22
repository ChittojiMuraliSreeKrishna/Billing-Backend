package com.example.billing.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.billing.model.Employee;
import com.example.billing.model.Password;
import com.example.billing.repository.EmployeeRepository;
import com.example.billing.repository.PasswordRepository;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setupPassword(String password, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        System.out.println("Setting up password: " + password + " for employee ID: " + employeeId);
    }
}