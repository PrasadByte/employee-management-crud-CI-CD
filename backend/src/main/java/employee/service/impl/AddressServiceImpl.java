package employee.service.impl;

import employee.entity.Address;
import employee.repository.AddressRepo;
import employee.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }


    @Override
    public Address createAddress(Address address) {
      log.info("address create request for address: {}", address);
     Address savedAddress =  addressRepo.save(address);
      log.info("address created successfully: {}", savedAddress);

      return savedAddress;
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address>adr = addressRepo.findAll();
        log.info("Fetched all addresses: {}", adr.size());
        return adr;
    }
}
