package com.employee.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressDto {
    private int id;
    private String city;
    private int pinCode;
    private int employeeId;
}
