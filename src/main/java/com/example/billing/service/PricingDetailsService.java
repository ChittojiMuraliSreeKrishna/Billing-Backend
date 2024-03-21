package com.example.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.PricingDetails;
import com.example.billing.modelDTO.PricingDetailsDTO;
import com.example.billing.repository.PricingDetailsRepository;

@Service
public class PricingDetailsService {

    private final PricingDetailsRepository pricingDetailsRepository;

    @Autowired
    public PricingDetailsService(PricingDetailsRepository pricingDetailsRepository) {
        this.pricingDetailsRepository = pricingDetailsRepository;
    }

    // Get pricing details by productId
    public PricingDetailsDTO getPricingDetailsByProductId(Long productId) {
        PricingDetails pricingDetails = pricingDetailsRepository.findByProduct_Id(productId);
        if (pricingDetails != null) {
            return convertToDTO(pricingDetails);
        }
        return null;
    }

    // Update pricing details by productId
    public PricingDetailsDTO updatePricingDetailsByProductId(Long productId, PricingDetailsDTO newPricingDetailsDTO) {
        PricingDetails existingPricingDetails = pricingDetailsRepository.findByProduct_Id(productId);
        if (existingPricingDetails != null) {
            updateEntityFromDTO(existingPricingDetails, newPricingDetailsDTO);
            PricingDetails updatedPricingDetails = pricingDetailsRepository.save(existingPricingDetails);
            return convertToDTO(updatedPricingDetails);
        }
        return null;
    }

    // Delete pricing details by productId
    public void deletePricingDetailsByProductId(Long productId) {
        pricingDetailsRepository.deleteByProduct_Id(productId);
    }

    // Helper method to convert entity to DTO
    private PricingDetailsDTO convertToDTO(PricingDetails pricingDetails) {
        PricingDetailsDTO pricingDetailsDTO = new PricingDetailsDTO();
        pricingDetailsDTO.setId(pricingDetails.getId());
        pricingDetailsDTO.setProductId(pricingDetails.getProduct().getId());
        pricingDetailsDTO.setPrice(pricingDetails.getPrice());
        pricingDetailsDTO.setStonePrice(pricingDetails.getStonePrice());
        pricingDetailsDTO.setMetalPrice(pricingDetails.getMetalPrice());
        pricingDetailsDTO.setVadd(pricingDetails.getVadd());
        pricingDetailsDTO.setVaddDiscount(pricingDetails.getVaddDiscount());
        pricingDetailsDTO.setTaxableAmount(pricingDetails.getTaxableAmount());
        pricingDetailsDTO.setCgst(pricingDetails.getCgst());
        pricingDetailsDTO.setSgst(pricingDetails.getSgst());
        return pricingDetailsDTO;
    }

    // Helper method to update entity from DTO
    private void updateEntityFromDTO(PricingDetails existingPricingDetails, PricingDetailsDTO newPricingDetailsDTO) {
        existingPricingDetails.setPrice(newPricingDetailsDTO.getPrice());
        existingPricingDetails.setStonePrice(newPricingDetailsDTO.getStonePrice());
        existingPricingDetails.setMetalPrice(newPricingDetailsDTO.getMetalPrice());
        existingPricingDetails.setVadd(newPricingDetailsDTO.getVadd());
        existingPricingDetails.setVaddDiscount(newPricingDetailsDTO.getVaddDiscount());
        existingPricingDetails.setTaxableAmount(newPricingDetailsDTO.getTaxableAmount());
        existingPricingDetails.setCgst(newPricingDetailsDTO.getCgst());
        existingPricingDetails.setSgst(newPricingDetailsDTO.getSgst());
    }
}
