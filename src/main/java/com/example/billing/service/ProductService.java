package com.example.billing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.PricingDetails;
import com.example.billing.model.Product;
import com.example.billing.modelDto.ProductWithPricingDTO;
import com.example.billing.repository.PricingDetailsRepository;
import com.example.billing.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PricingDetailsRepository pricingDetailsRepository;

	// Create a new product
	public ProductWithPricingDTO createProduct(ProductWithPricingDTO productWithPricingDTO) {
		// Mapping Product entity
		Product product = new Product();
		product.setName(productWithPricingDTO.getProductName());
		product.setCategory(productWithPricingDTO.getProductCategory());
		product.setDescription(productWithPricingDTO.getProductDescription());
		product.setPurity(productWithPricingDTO.getProductPurity());
		product.setGrossWeight(productWithPricingDTO.getProductGrossWeight());
		product.setStoneWeight(productWithPricingDTO.getProductStoneWeight());
		product.setNetWeight(productWithPricingDTO.getProductNetWeight());
		product.setHsnCode(productWithPricingDTO.getProductHsnCode());
		Product savedProduct = productRepository.save(product);

		// Mapping Price Details entity
		PricingDetails pricingDetails = new PricingDetails();
		pricingDetails.setProduct(savedProduct);
		pricingDetails.setPrice(productWithPricingDTO.getProductPrice());
		pricingDetails.setStonePrice(productWithPricingDTO.getProductStonePrice());
		pricingDetails.setMetalValue(productWithPricingDTO.getProductMetalValue());
		pricingDetails.setVadd(productWithPricingDTO.getProductVadd());
		pricingDetails.setVaddDiscount(productWithPricingDTO.getProductVaddDiscount());
		pricingDetails.setCgst(productWithPricingDTO.getProductCgst());
		pricingDetails.setSgst(productWithPricingDTO.getProductSgst());
		pricingDetails.setTaxableAmount(productWithPricingDTO.getProductTaxableAmount());
		pricingDetailsRepository.save(pricingDetails);

		// Update the ID of the DTO
		productWithPricingDTO.setProductId(savedProduct.getId());
		return productWithPricingDTO;
	}

	// Get all products with pricing details
	public List<ProductWithPricingDTO> getAllProducts() {
		List<ProductWithPricingDTO> productWithPricingDtoList = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			ProductWithPricingDTO productWithPricingDTO = new ProductWithPricingDTO();
			productWithPricingDTO.setProductId(product.getId());
			productWithPricingDTO.setProductName(product.getName());
			productWithPricingDTO.setProductDescription(product.getDescription());
			productWithPricingDTO.setProductCategory(product.getCategory());
			productWithPricingDTO.setProductPurity(product.getPurity());
			productWithPricingDTO.setProductGrossWeight(product.getGrossWeight());
			productWithPricingDTO.setProductStoneWeight(product.getStoneWeight());
			productWithPricingDTO.setProductNetWeight(product.getNetWeight());
			productWithPricingDTO.setProductHsnCode(product.getHsnCode());

			PricingDetails pricingDetails = pricingDetailsRepository.findByProductId(product.getId());
			if (pricingDetails != null) {
				productWithPricingDTO.setProductPrice(pricingDetails.getPrice());
				productWithPricingDTO.setProductStonePrice(pricingDetails.getStonePrice());
				productWithPricingDTO.setProductMetalValue(pricingDetails.getMetalValue());
				productWithPricingDTO.setProductVadd(pricingDetails.getVadd());
				productWithPricingDTO.setProductVaddDiscount(pricingDetails.getVaddDiscount());
				productWithPricingDTO.setProductTaxableAmount(pricingDetails.getTaxableAmount());
				productWithPricingDTO.setProductCgst(pricingDetails.getCgst());
				productWithPricingDTO.setProductSgst(pricingDetails.getSgst());
			}
			productWithPricingDtoList.add(productWithPricingDTO);
		}
		return productWithPricingDtoList;
	}

	// Update product details
	public ProductWithPricingDTO updateProduct(Long productId, ProductWithPricingDTO productWithPricingDTO) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product existingProduct = productOptional.get();
			existingProduct.setName(productWithPricingDTO.getProductName());
			existingProduct.setDescription(productWithPricingDTO.getProductDescription());
			existingProduct.setCategory(productWithPricingDTO.getProductCategory());
			existingProduct.setPurity(productWithPricingDTO.getProductPurity());
			existingProduct.setGrossWeight(productWithPricingDTO.getProductGrossWeight());
			existingProduct.setStoneWeight(productWithPricingDTO.getProductStoneWeight());
			existingProduct.setNetWeight(productWithPricingDTO.getProductNetWeight());
			existingProduct.setHsnCode(productWithPricingDTO.getProductHsnCode());
			productRepository.save(existingProduct);

			PricingDetails pricingDetails = pricingDetailsRepository.findByProduct_Id(productId);
			if (pricingDetails != null) {
				pricingDetails.setPrice(productWithPricingDTO.getProductPrice());
				pricingDetails.setStonePrice(productWithPricingDTO.getProductStonePrice());
				pricingDetails.setMetalValue(productWithPricingDTO.getProductMetalValue());
				pricingDetails.setVadd(productWithPricingDTO.getProductVadd());
				pricingDetails.setVaddDiscount(productWithPricingDTO.getProductVaddDiscount());
				pricingDetails.setCgst(productWithPricingDTO.getProductCgst());
				pricingDetails.setSgst(productWithPricingDTO.getProductSgst());
				pricingDetails.setTaxableAmount(productWithPricingDTO.getProductTaxableAmount());
				pricingDetailsRepository.save(pricingDetails);
			}
			productWithPricingDTO.setProductId(existingProduct.getId());
			return productWithPricingDTO;
		} else {
			return null;
		}
	}

	// Get Single Product with product Id
	public ProductWithPricingDTO getProductById(Long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			ProductWithPricingDTO productDTO = new ProductWithPricingDTO();
			productDTO.setProductId(product.getId());
			productDTO.setProductName(product.getName());
			productDTO.setProductDescription(product.getDescription());
			productDTO.setProductCategory(product.getCategory());
			productDTO.setProductPurity(product.getPurity());
			productDTO.setProductGrossWeight(product.getGrossWeight());
			productDTO.setProductStoneWeight(product.getStoneWeight());
			productDTO.setProductNetWeight(product.getNetWeight());
			productDTO.setProductHsnCode(product.getHsnCode());

			// Fetch pricing details by product ID
			PricingDetails pricingDetails = pricingDetailsRepository.findByProduct_Id(productId);
			if (pricingDetails != null) {
				productDTO.setProductPrice(pricingDetails.getPrice());
				productDTO.setProductStonePrice(pricingDetails.getStonePrice());
				productDTO.setProductMetalValue(pricingDetails.getMetalValue());
				productDTO.setProductVadd(pricingDetails.getVadd());
				productDTO.setProductVaddDiscount(pricingDetails.getVaddDiscount());
				productDTO.setProductTaxableAmount(pricingDetails.getTaxableAmount());
				productDTO.setProductCgst(pricingDetails.getCgst());
				productDTO.setProductSgst(pricingDetails.getSgst());
			}
			return productDTO;
		} else {
			return null;
		}
	}

	// Delete a product and its pricing details
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
		pricingDetailsRepository.deleteByProduct_Id(productId);
	}
}
