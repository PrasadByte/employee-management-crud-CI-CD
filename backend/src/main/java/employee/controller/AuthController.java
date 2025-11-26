package employee.controller;

import employee.entity.Employee;
import employee.repository.EmployeeRepo;
import employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/emp/auth")
public class AuthController {
    private final EmployeeRepo employeeRepo;
    private  final EmployeeService employeeService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(EmployeeRepo employeeRepo, EmployeeService employeeService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepo = employeeRepo;
        this.employeeService = employeeService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public Employee register(@RequestBody Employee user) {
        //return userRepository.save(user);
        return employeeService.registerEmployee(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee user) {

        Employee emp = employeeRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (bCryptPasswordEncoder.matches(user.getPassword(), emp.getPassword())) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(401).body("Invalid Password");
        }
    }


}
