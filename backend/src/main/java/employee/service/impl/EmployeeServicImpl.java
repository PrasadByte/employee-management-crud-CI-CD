package employee.service.impl;

import employee.dto.EmployeeDto;
import employee.entity.Employee;
import employee.repository.EmployeeRepo;
import employee.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServicImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper mapper;

    //add multiple employees
    @Override
    public List<Employee> createMultipleEmployee(List<Employee> employee) {
        log.info("Creating multiple employee: {}", employee);
        return employeeRepo.saveAll(employee);
    }

    //add single employee
    @Override
    public Employee createEmployee(Employee employee) {
        log.info("Creating employee: {}", employee);
        Optional.ofNullable(employee.getLaptop())
                        .ifPresent(lapy -> lapy.setEmployee(employee));


        Optional.ofNullable(employee.getAddresses())
                .ifPresent(addresses -> addresses.forEach(a -> a.setEmployee(employee)));

        Employee savedEmployee= employeeRepo.save(employee);
        log.info("Created employee: {}", savedEmployee);
        return savedEmployee;
    }

    //get employee by id
    @Override
    public Employee getEmployeeById(int id) {
        log.info("Fetching employee with id with : {}", id);
        Employee emp = employeeRepo.findById(id).orElseThrow(() ->
             new EntityNotFoundException("Id not found with "+id)
        );
        log.info("Found employee with id: {}", emp);
        return emp;
    }

    //find employee by name
    @Override
    public Optional<Employee> findByName(String name) {
        log.info("Finding employee with name: {}", name);
        Employee emp = employeeRepo.findByName(name)
                .orElseThrow(() ->
                    new EntityNotFoundException("name not found with "+ name)
                );
        log.info("Found employee with name: {}", emp);
        return Optional.of(emp);
    }

    //find employee by email
    @Override
    public Optional<Employee> findByEmail(String email) {
        log.info("Finding employee with email: {}", email);
        Employee emp = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found " + email));
        log.info("Found employee: {}", emp);
        return Optional.of(emp);
    }

    //get all employees
    @Override
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        List<Employee> employees = employeeRepo.findAll();
        log.info("Found {} employees", employees.size());
        return employees;
    }

    @Override
    public Employee updateEmployee(int id, Employee emp) {
        log.info("Employee fetching with id{}", id);
        Employee updateEmp = employeeRepo.findById(id).orElseThrow(() -> {
            log.info("id not found with {}", id);
            return new EntityNotFoundException("id not found with "+id);
        });
        updateEmp.setName(emp.getName());
        updateEmp.setNumber(emp.getNumber());
        updateEmp.setEmail(emp.getEmail());

        return employeeRepo.save(updateEmp);
    }

    @Override
    public void deleteEmployeeById(int id) {
        log.info("Deleting Employee with id{}", id);
        Employee newemp = employeeRepo.findById(id).orElseThrow(() -> {
            log.info("id not found with Employee {}", id);
            return new EntityNotFoundException("id not found with "+id);

        });
        employeeRepo.delete(newemp);
        log.info("Employee deleted with id{}", id);

    }

    @Override
    public EmployeeDto getEmployeeByidWithDto(int id) {
        log.info("Fetching employee with id: {}", id);
        Employee emp = employeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found with "+id)
        );
        return EmployeeDto.builder()
                .id(emp.getId())
                .name(emp.getName())
                .number(emp.getNumber())
                .email(emp.getEmail())
                .laptop(emp.getLaptop())
                .addresses(emp.getAddresses())
                .build();
    }

    @Override
    public List<EmployeeDto> getAllEmployeesWithDto() {
        log.info("Fetching all employees with DTO");
        List<EmployeeDto> employeeDtos = employeeRepo.findAll()
                .stream()
                .map(emp -> mapper.map(emp, EmployeeDto.class))
                .toList();

        log.info("Found {} employee DTOs", employeeDtos.size());
        return employeeDtos;

    }
}




