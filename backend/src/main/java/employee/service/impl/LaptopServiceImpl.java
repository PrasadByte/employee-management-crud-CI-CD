package employee.service.impl;

import employee.entity.Laptop;
import employee.repository.LaptopRepository;
import employee.service.LaptopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Slf4j
public class LaptopServiceImpl implements LaptopService {
private final LaptopRepository lapyRepository;
public LaptopServiceImpl(LaptopRepository lapyRepository){
    this.lapyRepository = lapyRepository;
}

    @Override
    public Laptop createNewLaptop(Laptop lapy) {
    log.info("request receive for creating nwe lapy {} ", lapy);
        return lapyRepository.save(lapy);
    }

    @Override
    public List<Laptop> getAllLaptops() {
    log.info("Fetching all Laptops");
        return lapyRepository.findAll();
    }
}
