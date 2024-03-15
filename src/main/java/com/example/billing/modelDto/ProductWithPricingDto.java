package com.example.billing.modelDto;

public class ProductWithPricingDto {
	private Long productId;
	private String productName;
	private String productCategory;
	private String productDescription;
	private Double productPrice;
	private Double productCgst;
	private Double productSgst;
	private Double productDiscount;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductCgst() {
		return productCgst;
	}

	public void setProductCgst(Double productCgst) {
		this.productCgst = productCgst;
	}

	public Double getProductSgst() {
		return productSgst;
	}

	public void setProductSgst(Double productSgst) {
		this.productSgst = productSgst;
	}

	public Double getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(Double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public ProductWithPricingDto(Long productId, String productName, String productCategory, String productDescription,
			Double productPrice, Double productCgst, Double productSgst, Double productDiscount) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCgst = productCgst;
		this.productSgst = productSgst;
		this.productDiscount = productDiscount;
	}

	public ProductWithPricingDto() {
		// TODO Auto-generated constructor stub
	}

}
