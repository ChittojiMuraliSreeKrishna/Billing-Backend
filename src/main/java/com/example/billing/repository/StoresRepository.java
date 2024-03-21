package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.Store;

public interface StoresRepository extends JpaRepository<Store, Long> {

}
