package com.example.billing.modelDto;

public class ProductWithPricingDTO {
	private Long productId;
	private String productName;
	private String productCategory;
	private String productDescription;
	private String productMaterial;
	private Double productPurity;
	private Double productPrice;
	private Double productCgst;
	private Double productSgst;
	private Double productVaddDiscount;
	private Double productVadd;
	private Double productTaxableAmount;
	private Double productMetalPrice;
	private Double productStonePrice;
	private Double productGrossWeight;
	private Double productStoneWeight;
	private Double productNetWeight;
	private String productHsnCode;
	private Long productStoreId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductMaterial() {
		return productMaterial;
	}

	public void setProductMaterial(String productMaterial) {
		this.productMaterial = productMaterial;
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

	public Double getProductVaddDiscount() {
		return productVaddDiscount;
	}

	public void setProductVaddDiscount(Double productVaddDiscount) {
		this.productVaddDiscount = productVaddDiscount;
	}

	public Double getProductVadd() {
		return productVadd;
	}

	public void setProductVadd(Double productVadd) {
		this.productVadd = productVadd;
	}

	public Double getProductTaxableAmount() {
		return productTaxableAmount;
	}

	public void setProductTaxableAmount(Double productTaxableAmount) {
		this.productTaxableAmount = productTaxableAmount;
	}

	public Double getProductMetalPrice() {
		return productMetalPrice;
	}

	public void setProductMetalPrice(Double productMetalPrice) {
		this.productMetalPrice = productMetalPrice;
	}

	public Double getProductStonePrice() {
		return productStonePrice;
	}

	public void setProductStonePrice(Double productStonePrice) {
		this.productStonePrice = productStonePrice;
	}

	public Double getProductPurity() {
		return productPurity;
	}

	public void setProductPurity(Double productPurity) {
		this.productPurity = productPurity;
	}

	public Double getProductGrossWeight() {
		return productGrossWeight;
	}

	public void setProductGrossWeight(Double productGrossWeight) {
		this.productGrossWeight = productGrossWeight;
	}

	public Double getProductStoneWeight() {
		return productStoneWeight;
	}

	public void setProductStoneWeight(Double productStoneWeight) {
		this.productStoneWeight = productStoneWeight;
	}

	public Double getProductNetWeight() {
		return productNetWeight;
	}

	public void setProductNetWeight(Double productNetWeight) {
		this.productNetWeight = productNetWeight;
	}

	public String getProductHsnCode() {
		return productHsnCode;
	}

	public void setProductHsnCode(String productHsnCode) {
		this.productHsnCode = productHsnCode;
	}

	public Long getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(Long productStoreId) {
		this.productStoreId = productStoreId;
	}


	public ProductWithPricingDTO(Long productId, String productName, String productCategory, String productDescription,
			Double productPrice, Double productCgst, Double productSgst, Double productVaddDiscount, Double productVadd,
			Double productTaxableAmount, Double productMetalPrice, Double productStonePrice, Double productPurity,
			Double productGrossWeight, Double productStoneWeight, Double productNetWeight, String productHsnCode,
			String productMaterial, Long productStoreId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCgst = productCgst;
		this.productSgst = productSgst;
		this.productVaddDiscount = productVaddDiscount;
		this.productVadd = productVadd;
		this.productTaxableAmount = productTaxableAmount;
		this.productMetalPrice = productMetalPrice;
		this.productStonePrice = productStonePrice;
		this.productPurity = productPurity;
		this.productGrossWeight = productGrossWeight;
		this.productStoneWeight = productStoneWeight;
		this.productNetWeight = productNetWeight;
		this.productHsnCode = productHsnCode;
		this.productMaterial = productMaterial;
		this.productStoreId = productStoreId;
	}

	public ProductWithPricingDTO() {
		// TODO Auto-generated constructor stub
	}

}
