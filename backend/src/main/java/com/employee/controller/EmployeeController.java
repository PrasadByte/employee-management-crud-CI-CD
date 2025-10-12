package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/v1/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto emp) {
      Employee newemp=  employeeService.createEmployee(emp);

    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            List<EmployeeDto> employees = employeeService.getAllEmployees();
            log.info("Successfully fetched {} employees.", employees.size());
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("Error while fetching all employees: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmp(@PathVariable int id, @RequestBody EmployeeDto emp) {
        try {
            // Pass both id and DTO to service
            employeeService.UpdateEmp(id, emp);

            log.info("Employee with ID {} updated successfully", id);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (RuntimeException e) {
            // If employee not found
            log.error("Employee with ID {} not found: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee with ID " + id + " not found.");
        } catch (Exception e) {
            // Generic error handling
            log.error("Error while updating employee with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update employee. Please try again later.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmpById(@PathVariable int id) {
        try {
            Optional<EmployeeDto> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                return ResponseEntity.ok(employee.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error while fetching employee by ID: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpById(@PathVariable int id) {
        try {
            boolean isDeleted = employeeService.deleteEmployeeById(id);

            if (isDeleted) {
                log.info("Employee with ID {} deleted successfully.", id);
                return ResponseEntity.ok("Employee deleted successfully");
            } else {
                log.warn("Employee with ID {} not found.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } catch (Exception e) {
            log.error("Error while deleting employee with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete employee. Please try again later.");
        }
    }

}