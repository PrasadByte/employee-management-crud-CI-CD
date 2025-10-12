package com.employee.service.impl;

import com.employee.Repositoy.EmployeeRepo;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, ModelMapper modelMapper) {

        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto entitytoDto(Employee emp) {

       // EmployeeDto empdto = modelMapper.map(emp, EmployeeDto.class);
//        EmployeeDto empdto = new EmployeeDto();
//        empdto.setId(emp.getId());
//        empdto.setUsername(emp.getUsername()); // manually mapping
//        empdto.setPassword(emp.getPassword());
//        empdto.setName(emp.getName());
//        empdto.setDepartment(emp.getDepartment());
//        empdto.setSalary(emp.getSalary());
        return modelMapper.map(emp, EmployeeDto.class);
    }

    @Override
    public Employee dtotoEntity(EmployeeDto empDto) {
//        Employee emp = new Employee();
//        emp.setId(empDto.getId());
//        emp.setUsername(empDto.getUsername());
//        emp.setPassword(empDto.getPassword());
//        emp.setName(empDto.getName());
//        emp.setDepartment(empDto.getDepartment());
//        emp.setSalary(empDto.getSalary());
        return modelMapper.map(empDto, Employee.class);
    }

    @Override
    public Employee createEmployee(EmployeeDto empdto) {
        log.info("Creating employee: {}", empdto);
        Employee emp = dtotoEntity(empdto);
      Employee newemp=  employeeRepo.save(emp);
        log.info("Employee created with ID: {}", newemp.getName());
        return newemp ;
    }
    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.info("Fetching all employees");
        List<Employee> userList = employeeRepo.findAll();
        log.info("Total employees found: {}", userList.size());
       return userList.stream().map(this::entitytoDto).toList();

    }

    @Override
    public void UpdateEmp(int id, EmployeeDto emp) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setUsername(emp.getUsername());
        employee.setPassword(emp.getPassword());
        employee.setName(emp.getName());
        employee.setDepartment(emp.getDepartment());
        employee.setSalary(emp.getSalary());
        employeeRepo.save(employee);
    }

    @Override
    public Optional<EmployeeDto> getEmployeeById(int id) {
        Employee emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        //return employeeRepo.findById(id);

        return Optional.of(entitytoDto(emp));
    }

    @Override
    public Boolean deleteEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isPresent()) {
            employeeRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
