package com.example.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.modelDto.ProductWithPricingDto;
import com.example.billing.service.ProductService;

@RestController
@RequestMapping("/inventory")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/create-product")
	public ResponseEntity<ProductWithPricingDto> createProduct(
			@RequestBody ProductWithPricingDto produtWithPricingDto) {
		ProductWithPricingDto createdProduct = productService.createProduct(produtWithPricingDto);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	@GetMapping("/list-products")
	public ResponseEntity<List<ProductWithPricingDto>> getAllProducts() {
		List<ProductWithPricingDto> productsPricing = productService.getAllProducts();
		return new ResponseEntity<>(productsPricing, HttpStatus.OK);
	}

	@PutMapping("/udpate-product")
	public ResponseEntity<ProductWithPricingDto> updateProduct(@PathVariable Long productId,
			@RequestBody ProductWithPricingDto productWithPricingDto) {
		ProductWithPricingDto updateProduct = productService.updateProduct(productId, productWithPricingDto);
		if (updateProduct != null) {
			return new ResponseEntity<>(updateProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
