package com.employee.controller;

import com.employee.dto.AddressDto;
import com.employee.service.impl.AddressServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    // ✅ Prasad change: Return AddressDto instead of plain String
    @PostMapping("/add")
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto add) {
        try {
            AddressDto saved = addressService.createAdd(add);
            return ResponseEntity.status(201).body(saved); // return saved object as JSON
        } catch (Exception e) {
            log.error("Error creating address: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> getAllAddress() {
        try {
            List<AddressDto> addressList = addressService.getAllAd();
            return ResponseEntity.ok(addressList);
        } catch (Exception e) {
            log.error("Error fetching addresses: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable int id) {
        try {
            Boolean isDeleted = addressService.deleteById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Address deleted successfully");
            } else {
                return ResponseEntity.status(404).body("Address not found");
            }
        } catch (Exception e) {
            log.error("Error deleting address: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Failed to delete address. Please try again later.");
        }
    }

    // ✅ Prasad change: Return updated AddressDto instead of String
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable int id, @RequestBody AddressDto addressDto) {
        try {
            AddressDto updated = addressService.updateAddress(id, addressDto);
            if (updated != null) {
                log.info("Address with ID {} updated successfully", id);
                return ResponseEntity.ok(updated); // return updated JSON
            } else {
                return ResponseEntity.status(404).build();
            }
        } catch (Exception e) {
            log.error("Error updating address with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
