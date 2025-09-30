package com.employee.service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    //entity to dto and dto to entity conversion methods can be added here if needed
    EmployeeDto entitytoDto(Employee emp);
    Employee dtotoEntity(EmployeeDto empDto);

    // Create a new employee
    void createEmployee(EmployeeDto emp);

    // Get all employees
    List<EmployeeDto> getAllEmployees();

    // Update an employee
    void UpdateEmp(int id ,EmployeeDto emp);

    // Get employee by ID
    Optional<EmployeeDto> getEmployeeById(int id);

    // Delete employee by ID
    Boolean deleteEmployeeById(int id);

    //find by username
}
