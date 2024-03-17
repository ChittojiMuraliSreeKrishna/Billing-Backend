package com.example.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.model.PricingDetails;
import com.example.billing.service.PricingDetailsService;

@RestController
@RequestMapping("/pricing-details")
public class PricingDetailsController {

	private final PricingDetailsService pricingDetailsService;

	@Autowired
	public PricingDetailsController(PricingDetailsService pricingDetailsService) {
		this.pricingDetailsService = pricingDetailsService;
	}

	@GetMapping("/{productId}")
	public PricingDetails getPricingDetailsByProductId(@PathVariable Long productId) {
		return pricingDetailsService.getPricingDetailsByProductId(productId);
	}

	@PutMapping("/{productId}")
	public PricingDetails updatePricingDetailsByProductId(@PathVariable Long productId,
			@RequestBody PricingDetails newPricingDetails) {
		return pricingDetailsService.updatePricingDetailsByProductId(productId, newPricingDetails);
	}

	@DeleteMapping("/{productId}")
	public void deletePricingDetailsByProductId(@PathVariable Long productId) {
		pricingDetailsService.deletePricingDetailsByProductId(productId);
	}
}
