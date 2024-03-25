package com.example.billing.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.Invoice;
import com.example.billing.repository.BillingRepository;

@Service
public class InvoiceService {

	@Autowired
	private ProductService productService;

	@Autowired
	private BillingRepository billingRepository;

	
	public List<Invoice> getAllBillings() {
		return billingRepository.findAll();
	}
	
	public Optional<Invoice> getBillingByBillingId(Long billingId) {
		return billingRepository.findById(billingId);
	}
	
	public Invoice createBilling(Invoice invoice) {
		return billingRepository.save(invoice);
	}

}
