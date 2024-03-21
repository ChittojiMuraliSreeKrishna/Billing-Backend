package com.example.billing.modelDTO;

import javax.validation.constraints.*;

public class PricingDetailsDTO {

    private Long id;

    @NotNull(message = "Product ID is required.")
    private Long productId;

    @NotNull(message = "Product price is required.")
    @Min(value = 0, message = "Product price must be non-negative.")
    private Double price;

    @NotNull(message = "Product stone price is required.")
    @Min(value = 0, message = "Product stone price must be non-negative.")
    private Double stonePrice;

    @NotNull(message = "Product metal price is required.")
    @Min(value = 0, message = "Product metal price must be non-negative.")
    private Double metalPrice;

    @NotNull(message = "Value added tax (VAT) is required.")
    @Min(value = 0, message = "Value added tax (VAT) must be non-negative.")
    private Double vadd;

    @Min(value = 0, message = "VAT discount must be non-negative.")
    private Double vaddDiscount;

    @NotNull(message = "Taxable amount is required.")
    @Min(value = 0, message = "Taxable amount must be non-negative.")
    private Double taxableAmount;

    @NotNull(message = "CGST is required.")
    @Min(value = 0, message = "CGST must be non-negative.")
    private Double cgst;

    @NotNull(message = "SGST is required.")
    @Min(value = 0, message = "SGST must be non-negative.")
    private Double sgst;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Double getMetalPrice() {
        return metalPrice;
    }

    public void setMetalPrice(Double metalPrice) {
        this.metalPrice = metalPrice;
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
