package com.example.billing.modelDTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {
    private long id;
    @NotBlank(message = "Employee First Name cannot be empty")
    private String firstName;
    
    @NotBlank(message = "Employee Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Employee Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotNull(message = "Employee Mobile No cannot be empty")
    @Size(min = 10, message = "Mobile number must have at least 10 digits")
    private Long mobileNo;
    
    @NotBlank(message = "Employee Address cannot be empty")
    private String address;
    
    @NotBlank(message = "Employee Role cannot be empty")
    private String role;
    
    private List<StoresDTO> stores;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public List<StoresDTO> getStores() {
        return stores;
    }
    public void setStores(List<StoresDTO> stores) {
        this.stores = stores;
    }

    // Constructors, getters, and setters
    
}
