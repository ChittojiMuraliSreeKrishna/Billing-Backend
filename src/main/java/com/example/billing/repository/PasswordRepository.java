package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.Password;

public interface PasswordRepository extends JpaRepository<Password, Long> {

}
