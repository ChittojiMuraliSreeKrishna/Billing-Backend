package com.example.billing.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PricingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@NotNull(message = "Product price is required.")
	@Min(value = 0, message = "Product price must be non-negative.")
	@Basic(optional = false)
	@Column(name = "item_price")
	private Double productPrice;

	@NotNull(message = "Product cgst is required.")
	@Min(value = 0, message = "Product cgst must be non-negative.")
	@Basic(optional = false)
	@Column(name = "item_cgst")
	private Double productCgst;

	@NotNull(message = "Product sgst is required.")
	@Min(value = 0, message = "Product sgst must be non-negative.")
	@Basic(optional = false)
	@Column(name = "item_sgst")
	private Double productSgst;

	@NotNull(message = "Product discount is required.")
	@Min(value = 0, message = "Product discount must be non-negative.")
	@Basic(optional = false)
	@Column(name = "item_discount")
	private Double productDiscount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

}
