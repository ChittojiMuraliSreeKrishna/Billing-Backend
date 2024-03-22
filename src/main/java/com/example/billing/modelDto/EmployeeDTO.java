package com.example.billing.modelDTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {
    private long id;
    @NotBlank(message = "Employee First Name cannot be empty")
    private String employeeFirstName;
    
    @NotBlank(message = "Employee Last Name cannot be empty")
    private String employeeLastName;

    @NotBlank(message = "Employee Email cannot be empty")
    @Email(message = "Invalid email format")
    private String employeeEmail;
    
    @NotNull(message = "Employee Mobile No cannot be empty")
    @Size(min = 10, message = "Mobile number must have at least 10 digits")
    private String employeeMobileNo;
    
    @NotBlank(message = "Employee Address cannot be empty")
    private String employeeAddress;
    
    @NotBlank(message = "Employee Role cannot be empty")
    private String employeeRole;
    
    private List<StoreDTO> employeeStores;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeMobileNo() {
        return employeeMobileNo;
    }

    public void setEmployeeMobileNo(String employeeMobileNo) {
        this.employeeMobileNo = employeeMobileNo;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public List<StoreDTO> getEmployeeStores() {
        return employeeStores;
    }

    public void setEmployeeStores(List<StoreDTO> employeeStores) {
        this.employeeStores = employeeStores;
    }        
    

}
