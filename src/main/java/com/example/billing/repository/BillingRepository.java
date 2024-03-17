package com.example.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billing.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
	@Override
	List<Billing> findAll();

	Billing findById(long billingId);
}
