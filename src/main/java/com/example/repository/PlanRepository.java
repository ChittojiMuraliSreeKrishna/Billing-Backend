package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.PlansEntity;

public interface PlanRepository extends JpaRepository<PlansEntity, Long> {

}
