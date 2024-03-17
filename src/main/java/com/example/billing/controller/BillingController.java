package com.example.billing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.model.Billing;
import com.example.billing.service.BillingService;

@RestController
@RequestMapping("/billings")
public class BillingController {

	@Autowired
	private BillingService billingService;

	@GetMapping
	public List<Billing> getAllBillings() {
		return billingService.getAllBillings();
	}

	@GetMapping("/{billingId}")
	public Optional<Billing> getBillingById(@PathVariable Long billingId) {
		return billingService.getBillingByBillingId(billingId);
	}

	@PostMapping
	public Billing createBilling(@RequestBody Billing billing) {
		return billingService.createBilling(billing);
	}
}
