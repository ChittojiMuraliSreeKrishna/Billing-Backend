package com.example.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billing.model.Invoice;

@Repository
public interface BillingRepository extends JpaRepository<Invoice, Long> {
	@Override
	List<Invoice> findAll();

	Invoice findById(long billingId);
}
