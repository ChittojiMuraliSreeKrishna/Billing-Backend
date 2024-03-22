package com.example.billing.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Employee First Name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Employee Last Name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Employee Email cannot be empty")
    @Column(name = "email", nullable = false)
    private String email;
    
    @NotNull(message = "Employee Mobile No cannot be empty")
    @Size(min = 10, message = "Mobile number must have at least 10 digits")
    @Column(name = "mobile_number", nullable = false)
    private String mobileNo;
    
    @NotBlank(message = "Employee Address cannot be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "Employee Role cannot be empty")
    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private List<Store> stores;

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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
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

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

   

   

    
}
