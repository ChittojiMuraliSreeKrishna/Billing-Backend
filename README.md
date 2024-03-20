# Billing-Backend
just a basic back-end spring boot application for managing general crud operations which are required for the billing software.
### dependencies
***
1. spring-boot-starter-data-jpa

1. spring-boot-starter-security

1. spring-boot-starter-web

1. spring-boot-devtools

1. pdfbox

1. PostgreSQL

1. Lombok

1. spring-boot-starter-test

1. spring-security-test

### Entities || Models
***
#### Product
```java
private long id;
private String name;
private String category;
private String description;
private String material;
private Double purity;
private Double grossWeight;
private Double stoneWeight;
private Double netWeight;
private String hsnCode;
```
#### Pricing Details
```java
private long id;
private Product product;
private Double price;
private Double stonePrice;
private Double metalPrice;
private Double vadd;
private Double vaddDiscount;
private Double taxableAmount;
private Double cgst;
private Double sgst;
```
#### productWithPricingDTO
```java
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
```
#### Invoice
```java
private long id;
private Set<Long> productIds;
private Double totalInvoice;
private Double discount;
private Double totalCgst;
private Double totalSgst;
private Double totalAmount;
private String customerName;
private Long customerMobileNo;
private String customerEmail;
private LocalDateTime billingDate;
```

### Services || Controllers
***
#### I. Inventory 
> @RequestMapping("/inventory")

|  S.No | Name | End-Path |
| --------- | ------- | ------- |
| 1. | Create Product | @PostMapping("/create-product") |
| 2. | List Products | @GetMapping("/list-products") |
| 3. | Update Product | @PutMapping("/udpate-product/{productId}") |
| 4. | Delete Product | @DeleteMapping("/delete-product/{productId}") |
| 5. | Get Product | @GetMapping("/get-product/{productId}") |
#### II. Invoice
> @RequestMapping("/billings")

| S.No | Name | End-Path |
| ----- | ----- | ------- |
| 1. | List Invoices | 	@GetMapping("/list-invoices") |
| 2. | Get Invoice | @GetMapping("/get-invoice/{billingId}") |
| 3. | Create Invoice | @PostMapping("/create-invoice") |
#### III. Pricing Details
> @RequestMapping("/pricing-details")

| S.No | Name | End-Path |
| ---- | ----- | -------- |
| 1. | Get Pricing | @GetMapping("/get-pricing/{productId}") |
| 2. | Update Pricing | @PutMapping("/update-pricing/{productId}") |
| 3. | Delete Pricing | @DeleteMapping("/delete-pricing/{productId}") |

## Invoice PDF
![Invoice](https://github.com/ChittojiMuraliSreeKrishna/Billing-Backend/assets/62329524/d3c5e1bb-4b27-44a1-a9e1-042bef02bda9)
