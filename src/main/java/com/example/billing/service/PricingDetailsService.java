package com.example.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.PricingDetails;
import com.example.billing.repository.PricingDetailsRepository;

@Service
public class PricingDetailsService {

	private final PricingDetailsRepository pricingDetailsRepository;

	@Autowired
	public PricingDetailsService(PricingDetailsRepository pricingDetailsRepository) {
		this.pricingDetailsRepository = pricingDetailsRepository;
	}

	// Get pricing details by productId
	public PricingDetails getPricingDetailsByProductId(Long productId) {
		return pricingDetailsRepository.findByProduct_Id(productId);
	}

	// Update pricing details by productId
	public PricingDetails updatePricingDetailsByProductId(Long productId, PricingDetails newPricingDetails) {
		PricingDetails existingPricingDetails = pricingDetailsRepository.findByProduct_Id(productId);
		if (existingPricingDetails != null) {
			existingPricingDetails.setPrice(newPricingDetails.getPrice());
			existingPricingDetails.setStonePrice(newPricingDetails.getStonePrice());
			existingPricingDetails.setMetalPrice(newPricingDetails.getMetalPrice());
			existingPricingDetails.setVadd(newPricingDetails.getVadd());
			existingPricingDetails.setVaddDiscount(newPricingDetails.getVaddDiscount());
			existingPricingDetails.setTaxableAmount(newPricingDetails.getTaxableAmount());
			existingPricingDetails.setCgst(newPricingDetails.getCgst());
			existingPricingDetails.setSgst(newPricingDetails.getSgst());
			// Save updated pricing details
			return pricingDetailsRepository.save(existingPricingDetails);
		}
		return null;
	}

	// Delete pricing details by productId
	public void deletePricingDetailsByProductId(Long productId) {
		pricingDetailsRepository.deleteByProduct_Id(productId);
	}
}
