package com.example.billing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.billing.model.Employee;
import com.example.billing.modelDTO.EmployeeDTO;
import com.example.billing.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        employee.setFirstName(employeeDTO.getEmployeeFirstName());
        employee.setLastName(employeeDTO.getEmployeeLastName());
        employee.setEmail(employeeDTO.getEmployeeEmail());
        employee.setMobileNo(employeeDTO.getEmployeeMobileNo());
        employee.setAddress(employeeDTO.getEmployeeAddress());
        employee.setRole(employeeDTO.getEmployeeRole());
        
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }



}

