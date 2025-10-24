package employee.service;

import employee.dto.EmployeeDto;
import employee.entity.Employee;
import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    //create employee
    List<Employee> createMultipleEmployee(List<Employee> employee);
    //create single employee
    Employee createEmployee(Employee employee);
    //get by id
    Employee getEmployeeById(int id);
    // find by name
    Optional<Employee>findByName(String name);
    //find by email
    Optional<Employee>findByEmail(String email);
    //get all employees\
    List<Employee>getAllEmployees();
    //update Employee
    Employee updateEmployee(int id, Employee emp);
    //delete by id with exception
    void deleteEmployeeById(int id);
    //get Employee By id;
    EmployeeDto getEmployeeByidWithDto(int id);
    //get All Employees With Dto
    List<EmployeeDto> getAllEmployeesWithDto();




}
