# Billing-Backend
just a basic back-end spring boot application for managing general crud operations which are required for the billing software.
### versions
> spring-boot: 2.2.6.RELEASE,

> java: 11
### dependencies

1. spring-boot-starter-data-jpa

1. spring-boot-starter-security

1. spring-boot-starter-web

1. spring-boot-devtools

1. pdfbox

1. postgresql

1. lombok

1. spring-boot-starter-test

1. spring-security-test
### Services
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
