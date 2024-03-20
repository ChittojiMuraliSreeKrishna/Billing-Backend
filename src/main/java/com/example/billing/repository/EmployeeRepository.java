package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billing.model.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

}
