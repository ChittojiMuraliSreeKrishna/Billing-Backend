package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.Stores;

public interface StoresRepository extends JpaRepository<Stores, Long> {

}
