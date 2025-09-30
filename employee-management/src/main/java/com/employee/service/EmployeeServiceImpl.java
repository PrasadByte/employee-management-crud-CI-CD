package com.employee.service;

import com.employee.Repositoy.EmployeeRepo;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
    public void createEmployee(EmployeeDto empdto) {
        Employee emp = dtotoEntity(empdto);
        employeeRepo.save(emp);
    }
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> userList = employeeRepo.findAll();
       return userList.stream().map(this::entitytoDto).toList();
//return userList;
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
      //  return Optional.of(entitytoDto(emp));
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
