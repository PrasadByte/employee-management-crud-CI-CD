package com.employee.service;

import com.employee.Repositoy.AddressRepo;
import com.employee.dto.AddressDto;
import com.employee.entity.Address;
import com.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public AddressDto entityToDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setPinCode(address.getPinCode());
        // ✅ Prasad change: Added proper mapping for employeeId
        dto.setEmployeeId(address.getEmployee().getId());
        return dto;
    }

    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCity(addressDto.getCity());
        address.setPinCode(addressDto.getPinCode());

        // ✅ Prasad change: Map employeeId to Employee object
        if (addressDto.getEmployeeId() > 0) {
            Employee employee = new Employee();
            employee.setId(addressDto.getEmployeeId()); // only set ID, JPA will attach
            address.setEmployee(employee);
        }
        return address;
    }

    @Override
    public AddressDto createAdd(AddressDto addressDto) {
        // ✅ Prasad change: Now dtoToEntity handles employee mapping
        Address address = dtoToEntity(addressDto);
        Address saved = addressRepo.save(address);
        return entityToDto(saved);
    }

    @Override
    public List<AddressDto> getAllAd() {
        return addressRepo.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto updateAddress(int id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            Address existing = optionalAddress.get();
            existing.setCity(addressDto.getCity());
            existing.setPinCode(addressDto.getPinCode());

            // ✅ Prasad change: Update employee if provided
            if (addressDto.getEmployeeId() > 0) {
                Employee employee = new Employee();
                employee.setId(addressDto.getEmployeeId());
                existing.setEmployee(employee);
            }

            Address updated = addressRepo.save(existing);
            return entityToDto(updated);
        }
        return null; // or throw custom exception
    }

    @Override
    public Optional<AddressDto> getAddById(int id) {
        return addressRepo.findById(id).map(this::entityToDto);
    }

    @Override
    public Boolean deleteById(int id) {
        if (addressRepo.existsById(id)) {
            addressRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
