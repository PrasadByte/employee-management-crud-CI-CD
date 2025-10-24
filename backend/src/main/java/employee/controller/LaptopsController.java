package employee.controller;

import employee.entity.Laptop;
import employee.service.LaptopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptop")
@CrossOrigin("localhost:4200")
@Slf4j
public class LaptopsController {
    private final LaptopService lapyService;
    public LaptopsController(LaptopService lapyService){
        this.lapyService = lapyService;
    }
    @PostMapping
    public ResponseEntity<Laptop> insertLaptop(@RequestBody Laptop lapy){
      log.info("Inserting new Laptops {} ",lapy);
      Laptop newLaptos = lapyService.createNewLaptop(lapy);
      log.info("laptops Inserted sucess fully {} ", newLaptos.getBrand());
     return new  ResponseEntity<>(newLaptos, HttpStatus.CREATED);
      /* return ResponseEntity.ok(newLaptos); */
    }
    @GetMapping
    public ResponseEntity<List<Laptop>> getAllLaptops(){
        log.info("Fetching all Laptops");
      List<Laptop> lapy =  lapyService.getAllLaptops();
        log.info("Fetched all Laptops");
      return new ResponseEntity<>(lapy, HttpStatus.OK);
     //   return ResponseEntity.ok(lapy);
    }
}
