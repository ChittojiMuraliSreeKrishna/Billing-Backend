package com.example.billing.modelDTO;

import javax.validation.constraints.*;

public class StoreDTO {

    private long storeId;

    @NotBlank(message = "Store Name Cannot be empty")
    private String storeName;

    @NotBlank(message = "Store Address Cannot be empty")
    private String storeAddress;

    @NotNull(message = "Store Phone Number Cannot be empty")
    @Size(min = 10, message = "Phone number must have at least 10 digits")
    private String storePhoneNumber;

    @NotBlank(message = "Store Area Cannot be empty")
    private String storeArea;

    @NotBlank(message = "Store Street Cannot be empty")
    private String storeStreet;

    @NotBlank(message = "Store City Cannot be empty")
    private String storeCity;

    @NotBlank(message = "Store District Cannot be empty")
    private String storeDistrict;

    @NotBlank(message = "Store State Cannot be empty")
    private String storeState;

    @NotBlank(message = "Store LandMark Cannot be empty")
    private String storeLandMark;

    @NotNull(message = "Store PinCode cannot be empty")
    @Size(min = 6, message = "Pin code must have at least 6 digits")
    private String storePinCode;

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
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

    public String getStorePhoneNumber() {
        return storePhoneNumber;
    }

    public void setStorePhoneNumber(String storePhoneNumber) {
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

    public String getStorePinCode() {
        return storePinCode;
    }

    public void setStorePinCode(String storePinCode) {
        this.storePinCode = storePinCode;
    }

     public StoreDTO() {
    }

    public StoreDTO(long storeId, String storeName, String storeAddress, String storePhoneNumber, String storeArea,
                    String storeStreet, String storeCity, String storeDistrict, String storeState,
                    String storeLandMark, String storePinCode) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePhoneNumber = storePhoneNumber;
        this.storeArea = storeArea;
        this.storeStreet = storeStreet;
        this.storeCity = storeCity;
        this.storeDistrict = storeDistrict;
        this.storeState = storeState;
        this.storeLandMark = storeLandMark;
        this.storePinCode = storePinCode;
    }

   
}
