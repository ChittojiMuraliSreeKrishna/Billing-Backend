package com.example.billing.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pricing")
@Data
@NoArgsConstructor
public class PricingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@NotNull(message = "Product price is required.")
	@Min(value = 0, message = "Product price must be non-negative.")
	@Column(name = "price")
	private Double price;

	@NotNull(message = "Product stone price is required.")
	@Min(value = 0, message = "Product stone price must be non-negative.")
	@Column(name = "stone_price")
	private Double stonePrice;

	@NotNull(message = "Product metal value is required.")
	@Min(value = 0, message = "Product metal value must be non-negative.")
	@Column(name = "metal_value")
	private Double metalValue;

	@NotNull(message = "Value added tax (VAT) is required.")
	@Min(value = 0, message = "Value added tax (VAT) must be non-negative.")
	@Column(name = "vadd")
	private Double vadd;

	@Min(value = 0, message = "VAT discount must be non-negative.")
	@Column(name = "vadd_discount")
	private Double vaddDiscount;

	@NotNull(message = "Taxable amount is required.")
	@Min(value = 0, message = "Taxable amount must be non-negative.")
	@Column(name = "taxable_amount")
	private Double taxableAmount;

	@NotNull(message = "CGST is required.")
	@Min(value = 0, message = "CGST must be non-negative.")
	@Column(name = "cgst")
	private Double cgst;

	@NotNull(message = "SGST is required.")
	@Min(value = 0, message = "SGST must be non-negative.")
	@Column(name = "sgst")
	private Double sgst;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStonePrice() {
		return stonePrice;
	}

	public void setStonePrice(Double stonePrice) {
		this.stonePrice = stonePrice;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Double getVadd() {
		return vadd;
	}

	public void setVadd(Double vadd) {
		this.vadd = vadd;
	}

	public Double getVaddDiscount() {
		return vaddDiscount;
	}

	public void setVaddDiscount(Double vaddDiscount) {
		this.vaddDiscount = vaddDiscount;
	}

	public Double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public Double getCgst() {
		return cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

}
