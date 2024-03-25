package com.example.billing.service;

import com.example.billing.model.Password;
import com.example.billing.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository, PasswordEncoder passwordEncoder) {
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Password createPassword(Password password) {
        // Hash the password before saving it
        String hashedPassword = passwordEncoder.encode(password.getAuthenticationPassword());
        password.setAuthenticationPassword(hashedPassword);
        return passwordRepository.save(password);
    }

    public Optional<Password> getPasswordById(long id) {
        return passwordRepository.findById(id);
    }

    public void deletePassword(long id) {
        passwordRepository.deleteById(id);
    }

    public Password updatePassword(long id, Password newPassword) {
        newPassword.setId(id);
        return passwordRepository.save(newPassword);
    }

    public boolean authenticate(String email, String password) {
        // Find the password entity associated with the given email
        Password passwordEntity = passwordRepository.findByEmployeeEmail(email);
        
        // Check if a password entity exists and if the provided password matches the hashed password
        return passwordEntity != null && passwordEncoder.matches(password, passwordEntity.getAuthenticationPassword());
    }
}
