package com.example.billing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.model.Invoice;
import com.example.billing.service.InvoiceService;

@RestController
@RequestMapping("/billings")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping
	public List<Invoice> getAllBillings() {
		return invoiceService.getAllBillings();
	}

	@GetMapping("/{billingId}")
	public Optional<Invoice> getBillingById(@PathVariable Long billingId) {
		return invoiceService.getBillingByBillingId(billingId);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createBilling(@RequestBody Invoice invoice) {
		invoiceService.createBilling(invoice);
		invoiceService.generatePDFAndSendEmail(invoice);
		return ResponseEntity.ok("Invoice created successfully and bill generated.");

	}
}
