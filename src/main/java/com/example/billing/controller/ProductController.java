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

import com.example.billing.modelDTO.ProductWithPricingDTO;
import com.example.billing.service.ProductService;

@RestController
@RequestMapping("/inventory")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/create-product")
	public ResponseEntity<ProductWithPricingDTO> createProduct(
			@RequestBody ProductWithPricingDTO produtWithPricingDto) {
		ProductWithPricingDTO createdProduct = productService.createProduct(produtWithPricingDto);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	@GetMapping("/list-products")
	public ResponseEntity<List<ProductWithPricingDTO>> getAllProducts() {
		List<ProductWithPricingDTO> productsPricing = productService.getAllProducts();
		return new ResponseEntity<>(productsPricing, HttpStatus.OK);
	}

	@PutMapping("/udpate-product/{productId}")
	public ResponseEntity<ProductWithPricingDTO> updateProduct(@PathVariable Long productId,
			@RequestBody ProductWithPricingDTO productWithPricingDTO) {
		ProductWithPricingDTO updateProduct = productService.updateProduct(productId, productWithPricingDTO);
		if (updateProduct != null) {
			return new ResponseEntity<>(updateProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get-product/{productId}")
	public ResponseEntity<ProductWithPricingDTO> getProductById(@PathVariable Long productId) {
		ProductWithPricingDTO product = productService.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-product/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
