package com.example.billing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.billing.model.PricingDetails;
import com.example.billing.model.Product;
import com.example.billing.modelDto.ProductWithPricingDTO;
import com.example.billing.repository.PricingDetailsRepository;
import com.example.billing.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PricingDetailsRepository pricingDetailsRepository;

	public ProductWithPricingDTO createProduct(ProductWithPricingDTO productWithPricingDTO) {
		Product product = mapToProduct(productWithPricingDTO);
		Product savedProduct = productRepository.save(product);
		PricingDetails pricingDetails = mapToPricingDetails(productWithPricingDTO, savedProduct);
		pricingDetailsRepository.save(pricingDetails);
		productWithPricingDTO.setProductId(savedProduct.getId());
		return productWithPricingDTO;
	}

	public List<ProductWithPricingDTO> getAllProducts() {
		List<ProductWithPricingDTO> productWithPricingDtoList = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			ProductWithPricingDTO productWithPricingDTO = mapToProductWithPricingDTO(product);
			productWithPricingDtoList.add(productWithPricingDTO);
		}
		return productWithPricingDtoList;
	}

	public ProductWithPricingDTO updateProduct(Long productId, ProductWithPricingDTO productWithPricingDTO) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product existingProduct = productOptional.get();
			mapProductUpdate(existingProduct, productWithPricingDTO);
			productRepository.save(existingProduct);
			PricingDetails pricingDetails = pricingDetailsRepository.findByProduct_Id(productId);
			if (pricingDetails != null) {
				mapPricingDetailsUpdate(pricingDetails, productWithPricingDTO);
				pricingDetailsRepository.save(pricingDetails);
			}
			productWithPricingDTO.setProductId(existingProduct.getId());
			return productWithPricingDTO;
		} else {
			return null; // or throw an exception
		}
	}

	public ProductWithPricingDTO getProductById(Long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			ProductWithPricingDTO productDTO = mapToProductWithPricingDTO(product);
			return productDTO;
		} else {
			return null; // or throw an exception
		}
	}

	public ResponseEntity<String> deleteProduct(Long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			pricingDetailsRepository.deleteByProduct_Id(productId);
			productRepository.delete(product);
			return ResponseEntity.ok("Product with ID " + productId + " deleted successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + productId + " not found.");
		}
	}

	private Product mapToProduct(ProductWithPricingDTO productWithPricingDTO) {
		Product product = new Product();
		product.setName(productWithPricingDTO.getProductName());
		product.setDescription(productWithPricingDTO.getProductDescription());
		product.setCategory(productWithPricingDTO.getProductCategory());
		product.setPurity(productWithPricingDTO.getProductPurity());
		product.setGrossWeight(productWithPricingDTO.getProductGrossWeight());
		product.setStoneWeight(productWithPricingDTO.getProductStoneWeight());
		product.setNetWeight(productWithPricingDTO.getProductNetWeight());
		product.setHsnCode(productWithPricingDTO.getProductHsnCode());
		product.setMaterial(productWithPricingDTO.getProductMaterial());
		return product;
	}

	private PricingDetails mapToPricingDetails(ProductWithPricingDTO productWithPricingDTO, Product savedProduct) {
		PricingDetails pricingDetails = new PricingDetails();
		pricingDetails.setPrice(productWithPricingDTO.getProductPrice());
		pricingDetails.setStonePrice(productWithPricingDTO.getProductStonePrice());
		pricingDetails.setMetalPrice(productWithPricingDTO.getProductMetalPrice());
		pricingDetails.setVadd(productWithPricingDTO.getProductVadd());
		pricingDetails.setVaddDiscount(productWithPricingDTO.getProductVaddDiscount());
		pricingDetails.setCgst(productWithPricingDTO.getProductCgst());
		pricingDetails.setSgst(productWithPricingDTO.getProductSgst());
		pricingDetails.setTaxableAmount(productWithPricingDTO.getProductTaxableAmount());
		pricingDetails.setProduct(savedProduct);
		return pricingDetails;
	}

	private ProductWithPricingDTO mapToProductWithPricingDTO(Product product) {
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
		productWithPricingDTO.setProductMaterial(product.getMaterial());
		return productWithPricingDTO;
	}

	private void mapProductUpdate(Product product, ProductWithPricingDTO productWithPricingDTO) {
		product.setName(productWithPricingDTO.getProductName());
		product.setDescription(productWithPricingDTO.getProductDescription());
		product.setCategory(productWithPricingDTO.getProductCategory());
		product.setPurity(productWithPricingDTO.getProductPurity());
		product.setGrossWeight(productWithPricingDTO.getProductGrossWeight());
		product.setStoneWeight(productWithPricingDTO.getProductStoneWeight());
		product.setNetWeight(productWithPricingDTO.getProductNetWeight());
		product.setHsnCode(productWithPricingDTO.getProductHsnCode());
		product.setMaterial(productWithPricingDTO.getProductMaterial());
	}

	private void mapPricingDetailsUpdate(PricingDetails pricingDetails, ProductWithPricingDTO productWithPricingDTO) {
		pricingDetails.setPrice(productWithPricingDTO.getProductPrice());
		pricingDetails.setStonePrice(productWithPricingDTO.getProductStonePrice());
		pricingDetails.setMetalPrice(productWithPricingDTO.getProductMetalPrice());
		pricingDetails.setVadd(productWithPricingDTO.getProductVadd());
		pricingDetails.setVaddDiscount(productWithPricingDTO.getProductVaddDiscount());
		pricingDetails.setCgst(productWithPricingDTO.getProductCgst());
		pricingDetails.setSgst(productWithPricingDTO.getProductSgst());
		pricingDetails.setTaxableAmount(productWithPricingDTO.getProductTaxableAmount());
	}
}
