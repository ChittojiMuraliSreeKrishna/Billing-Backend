package com.example.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.Billing;
import com.example.billing.repository.BillingRepository;

@Service
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;

	public List<Billing> getAllBillings() {
		return billingRepository.findAll();
	}

	public Optional<Billing> getBillingByBillingId(Long billingId) {
		return billingRepository.findById(billingId);
	}

	public Billing createBilling(Billing billing) {
		return billingRepository.save(billing);
	}
}
