package employee.controller;

import employee.entity.Address;
import employee.entity.Employee;
import employee.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("localhost:4200")
@RestController
@RequestMapping("/api/addr")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public ResponseEntity<Address> insertAddress(@RequestBody Address address){
        log.info("Received request to create address: {}", address);
        Address addr = addressService.createAddress(address);
        log.info("Address created successfully: {}", addr);
        return ResponseEntity.ok(addr);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Address>>getAllAddresses(){
        log.info("Received request to fetch all addresses");
        List<Address> addresses = addressService.getAllAddresses();
        log.info("Fetched {} addresses", addresses.size());
        return ResponseEntity.ok(addresses);
    }
}
