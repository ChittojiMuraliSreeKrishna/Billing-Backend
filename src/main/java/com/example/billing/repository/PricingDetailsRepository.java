package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.PricingDetails;

public interface PricingDetailsRepository extends JpaRepository<PricingDetails, Long> {

	PricingDetails findByProductId(long id);

	void deleteByProductId(Long productId);
}
