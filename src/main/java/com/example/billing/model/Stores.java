package com.example.billing.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Store Name Cannot be empty")
    @Column(name = "name", nullable = false)
    private String storeName;

    @NotBlank(message = "Store Address Cannot be empty")
    @Column(name = "address", nullable = false)
    private String storeAddress;

    @NotNull(message = "Store Phone Number Cannot be empty")
    @Size(min = 10, message = "Phone number must have at least 10 digits")
    @Column(name = "phone_number", nullable = false)
    private Long storePhoneNumber;

    @NotBlank(message = "Store Area Cannot be empty")
    @Column(name = "area", nullable = false)
    private String storeArea;

    @NotBlank(message = "Store Street Cannot be empty")
    @Column(name = "street", nullable = false)
    private String storeStreet;
    
    @NotBlank(message = "Store City Cannot be empty")
    @Column(name = "city", nullable = false)
    private String storeCity;

    @NotBlank(message = "Store District Cannot be empty")
    @Column(name = "district", nullable = false)
    private String storeDistrict;

    @NotBlank(message = "Store State Cannot be empty")
    @Column(name = "state", nullable = false)
    private String storeState;

    @NotBlank(message = "Store LandMark Cannot be empty")
    @Column(name = "landmark", nullable = false)
    private String storeLandMark;

    @NotNull(message = "Store PinCode cannot be empty")
    @Size(min = 6, message = "Pin code must have at least 6 digits")
    @Column(name = "pincode", nullable = false)
    private Long storePinCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Long getStorePhoneNumber() {
        return storePhoneNumber;
    }

    public void setStorePhoneNumber(Long storePhoneNumber) {
        this.storePhoneNumber = storePhoneNumber;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getStoreStreet() {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet) {
        this.storeStreet = storeStreet;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreDistrict() {
        return storeDistrict;
    }

    public void setStoreDistrict(String storeDistrict) {
        this.storeDistrict = storeDistrict;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public String getStoreLandMark() {
        return storeLandMark;
    }

    public void setStoreLandMark(String storeLandMark) {
        this.storeLandMark = storeLandMark;
    }

    public Long getStorePinCode() {
        return storePinCode;
    }

    public void setStorePinCode(Long storePinCode) {
        this.storePinCode = storePinCode;
    }

    

}
