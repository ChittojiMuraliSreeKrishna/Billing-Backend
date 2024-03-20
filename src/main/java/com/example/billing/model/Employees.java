package com.example.billing.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Employee First Name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String employeeFirstName;

    @NotBlank(message = "Employee Last Name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String employeeLastName;

    @NotBlank(message = "Employee Email cannot be empty")
    @Column(name = "email", nullable = false)
    private String employeeEmail;
    
    @NotNull(message = "Employee Mobile No cannot be empty")
    @Size(min = 10, message = "Mobile number must have at least 10 digits")
    @Column(name = "mobile_number", nullable = false)
    private Long employeeMobileNo;
    
    @NotBlank(message = "Employee Address cannot be empty")
    @Column(name = "address", nullable = false)
    private String employeeAddress;

    @NotBlank(message = "Employee Role cannot be empty")
    @Column(name = "role", nullable = false)
    private String employeeRole;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private List<Stores> stores;

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

    public Long getEmployeeMobileNo() {
        return employeeMobileNo;
    }

    public void setEmployeeMobileNo(Long employeeMobileNo) {
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

    public List<Stores> getStores() {
        return stores;
    }

    public void setStores(List<Stores> stores) {
        this.stores = stores;
    }


    
}
