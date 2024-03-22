package com.example.billing.controller;

import com.example.billing.model.Password;
import com.example.billing.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    public ResponseEntity<Password> createPassword(@RequestBody Password password) {
        Password createdPassword = passwordService.createPassword(password);
        return new ResponseEntity<>(createdPassword, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Password>> getAllPasswords() {
        List<Password> passwords = passwordService.getAllPasswords();
        return new ResponseEntity<>(passwords, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Password> getPasswordById(@PathVariable("id") long id) {
        Optional<Password> password = passwordService.getPasswordById(id);
        return password.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassword(@PathVariable("id") long id) {
        passwordService.deletePassword(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Password> updatePassword(@PathVariable("id") long id, @RequestBody Password newPassword) {
        Password updatedPassword = passwordService.updatePassword(id, newPassword);
        return new ResponseEntity<>(updatedPassword, HttpStatus.OK);
    }
}
