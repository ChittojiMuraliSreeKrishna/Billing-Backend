package com.example.billing.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Store Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Store Address cannot be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "Store Area cannot be empty")
    @Column(name = "area", nullable = false)
    private String area;

    @NotBlank(message = "Store Street cannot be empty")
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank(message = "Store City cannot be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Store District cannot be empty")
    @Column(name = "district", nullable = false)
    private String district;

    @NotBlank(message = "Store State cannot be empty")
    @Column(name = "state", nullable = false)
    private String state;

    @NotBlank(message = "Store Landmark cannot be empty")
    @Column(name = "landmark", nullable = false)
    private String landmark;

    @NotNull(message = "Store Phone Number cannot be empty")
    @Size(min = 10, message = "Phone number must have at least 10 digits")
    @Column(name = "phone_number", nullable = false, unique = true) // Assuming phone numbers are unique
    private String phoneNumber;

    @NotNull(message = "Store Pin Code cannot be empty")
    @Size(min = 6, message = "Pin code must have at least 6 digits")
    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    // Getters, and setters

    
}
