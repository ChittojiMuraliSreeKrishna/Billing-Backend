package com.example.billing.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "billing")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ElementCollection
	@Column(name = "product_ids", nullable = false)
	private Set<Long> productIds;

	@NotNull(message = "Product invoice amount cannot be empty.")
	@Column(name = "total_invoice", nullable = false)
	private Double totalInvoice;

	@Column(name = "discount")
	private Double discount;

	@NotNull(message = "Product total CGST cannot be empty.")
	@Column(name = "total_cgst", nullable = false)
	private Double totalCgst;

	@NotNull(message = "Product total SGST cannot be empty.")
	@Column(name = "total_sgst", nullable = false)
	private Double totalSgst;

	@NotNull(message = "Product total amount cannot be empty.")
	@Column(name = "total_amount", nullable = false)
	private Double totalAmount;

	@NotNull(message = "Customer name cannot be empty.")
	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@NotNull(message = "Customer mobile number cannot be empty.")
	@Column(name = "customer_mobile_no", nullable = false)
	private Long customerMobileNo;

	@Column(name = "customer_email", nullable = true)
	private String customerEmail;

	@Column(name = "billing_date", nullable = false, updatable = false)
	private LocalDateTime billingDate;

	@PrePersist
	protected void onCreate() {
		billingDate = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(Set<Long> productIds) {
		this.productIds = productIds;
	}

	public Double getTotalInvoice() {
		return totalInvoice;
	}

	public void setTotalInvoice(Double totalInvoice) {
		this.totalInvoice = totalInvoice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotalCgst() {
		return totalCgst;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setTotalCgst(Double totalCgst) {
		this.totalCgst = totalCgst;
	}

	public Double getTotalSgst() {
		return totalSgst;
	}

	public void setTotalSgst(Double totalSgst) {
		this.totalSgst = totalSgst;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(Long customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public LocalDateTime getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDateTime billingDate) {
		this.billingDate = billingDate;
	}

}
