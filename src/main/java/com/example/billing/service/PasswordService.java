package com.example.billing.service;

import com.example.billing.model.Password;
import com.example.billing.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public Password createPassword(Password password) {
        return passwordRepository.save(password);
    }

    public List<Password> getAllPasswords() {
        return passwordRepository.findAll();
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
}
