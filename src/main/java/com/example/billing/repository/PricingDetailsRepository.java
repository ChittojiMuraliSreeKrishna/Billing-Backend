package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.PricingDetails;

public interface PricingDetailsRepository extends JpaRepository<PricingDetails, Long> {

	PricingDetails findByProduct_Id(Long productId);

	void deleteByProduct_Id(Long productId);
}
