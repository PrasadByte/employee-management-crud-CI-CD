package com.employee.service;

import com.employee.dto.AddressDto;
import com.employee.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    // Convert Entity to DTO
    AddressDto entityToDto(Address address);

    // Convert DTO to Entity
    Address dtoToEntity(AddressDto addressDto);

    // Create new Address
    AddressDto createAdd(AddressDto addressDto);

    // Get all Addresses
    List<AddressDto> getAllAd();

    // Update Address
    AddressDto updateAddress(int id, AddressDto addressDto);

    // Get Address by id
    Optional<AddressDto> getAddById(int id);

    // Delete Address by id
    Boolean deleteById(int id);
}
