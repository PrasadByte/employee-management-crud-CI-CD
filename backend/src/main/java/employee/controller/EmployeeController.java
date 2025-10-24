package employee.controller;

import employee.dto.EmployeeDto;
import employee.entity.Employee;
import employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("localhost:4200")
@RestController
@Slf4j
@RequestMapping("/api/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;



    @PostMapping("/addAll")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
        log.info("Received request to create {} employees", employees.size());
        List<Employee> createdEmployees = employeeService.createMultipleEmployee(employees);
        return new ResponseEntity<>(createdEmployees, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        log.info("Received request to create employee: {}", employee);
        Employee createdEmployee = employeeService.createEmployee(employee);
        log.info("Created employee: {}", createdEmployee);
       return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
            log.info("Received request to get employee with id: {}", id);
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);

    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Employee> findByName(@PathVariable String name) {
   Optional<Employee> emp = employeeService.findByName(name);
   return emp.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        log.info("Received request to get all employees");
        Iterable<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee emp){
        log.info("Received request to update  employee with id: {}", id);
       Employee updated = employeeService.updateEmployee(id, emp);
       return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteEmployeeWithId(@PathVariable int id) {
        log.info("Received request to delete employee with id: {}", id);
        employeeService.deleteEmployeeById(id);
        log.info("Deleted employee with id: {}", id);
        return new ResponseEntity<>("Employee deleted with id: " + id, HttpStatus.OK);
    }
    @GetMapping("/dto/{id}")
    public ResponseEntity<?> getEmployeeByIdWithDto(@PathVariable int id) {
        log.info("Received request to get employee DTO with id: {}", id);
        EmployeeDto empDto = employeeService.getEmployeeByidWithDto(id);
        return new ResponseEntity<>(empDto, HttpStatus.OK);
    }
    @GetMapping("/dto/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesWithDto() {
        log.info("Received request to get all employee DTOs");
        List<EmployeeDto> empDtos = employeeService.getAllEmployeesWithDto();
        return new ResponseEntity<>(empDtos, HttpStatus.OK);
    }



}
