package com.example.billing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing.model.PricingDetails;
import com.example.billing.model.Product;
import com.example.billing.modelDto.ProductWithPricingDto;
import com.example.billing.repository.PricingDetailsRepository;
import com.example.billing.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PricingDetailsRepository pricingDetailsRepository;

	// For Creating Product
	public ProductWithPricingDto createProduct(ProductWithPricingDto productWithPricingDto) {

		// Mapping Product entity
		Product product = new Product();
		product.setProductName(productWithPricingDto.getProductName());
		product.setProductCategory(productWithPricingDto.getProductCategory());
		product.setProductDescription(productWithPricingDto.getProductDescription());
		Product savedProduct = productRepository.save(product);

		// Mapping Price Details entity
		PricingDetails pricingDetails = new PricingDetails();
		pricingDetails.setProduct(savedProduct);
		pricingDetails.setProductPrice(productWithPricingDto.getProductPrice());
		pricingDetails.setProductCgst(productWithPricingDto.getProductCgst());
		pricingDetails.setProductSgst(productWithPricingDto.getProductSgst());
		pricingDetails.setProductDiscount(productWithPricingDto.getProductDiscount());
		@SuppressWarnings("unused")
		PricingDetails savedPricingDetails = pricingDetailsRepository.save(pricingDetails);

		// Updating Id of Product
		productWithPricingDto.setProductId(savedProduct.getId());
		return productWithPricingDto;
	}

	// For getting All Products
	public List<ProductWithPricingDto> getAllProducts() {
		List<ProductWithPricingDto> productWithPricingDtoList = new ArrayList<>();

		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			ProductWithPricingDto productWithPricingDto = new ProductWithPricingDto();
			productWithPricingDto.setProductId(product.getId());
			productWithPricingDto.setProductName(product.getProductName());
			productWithPricingDto.setProductDescription(product.getProductDescription());
			productWithPricingDto.setProductCategory(product.getProductCategory());

			PricingDetails pricingDetails = pricingDetailsRepository.findByProductId(product.getId());
			if (pricingDetails != null) {
				productWithPricingDto.setProductPrice(pricingDetails.getProductPrice());
				productWithPricingDto.setProductCgst(pricingDetails.getProductCgst());
				productWithPricingDto.setProductSgst(pricingDetails.getProductSgst());
				productWithPricingDto.setProductDiscount(pricingDetails.getProductDiscount());
			}

			productWithPricingDtoList.add(productWithPricingDto);
		}

		return productWithPricingDtoList;
	}

	// For updating the Product
	public ProductWithPricingDto updateProduct(Long productId, ProductWithPricingDto productWithPricingDto) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product existingProduct = productOptional.get();
			existingProduct.setProductName(productWithPricingDto.getProductName());
			existingProduct.setProductDescription(productWithPricingDto.getProductDescription());
			existingProduct.setProductCategory(productWithPricingDto.getProductCategory());
			Product savedProduct = productRepository.save(existingProduct);

			PricingDetails pricingDetails = pricingDetailsRepository.findByProductId(productId);
			if (pricingDetails != null) {
				pricingDetails.setProductPrice(productWithPricingDto.getProductPrice());
				pricingDetails.setProductCgst(productWithPricingDto.getProductCgst());
				pricingDetails.setProductSgst(productWithPricingDto.getProductSgst());
				pricingDetails.setProductDiscount(productWithPricingDto.getProductDiscount());
				pricingDetailsRepository.save(pricingDetails);
			}
			productWithPricingDto.setProductId(savedProduct.getId());
			return productWithPricingDto;
		} else {
			return null;
		}
	}

	// For Deleting the Product
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
		pricingDetailsRepository.deleteByProductId(productId);
	}

}
