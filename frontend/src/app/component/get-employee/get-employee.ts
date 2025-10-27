import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Employee } from '../../model/employee.model';
import { EmployeeService } from '../../service/employee-service';
import { AddressService } from '../../service/address-service';
import { Address } from '../../model/address.model';

@Component({
  selector: 'app-get-employee',
  standalone: true,                               // ✅ standalone
  imports: [CommonModule],      // ✅ Angular modules ONLY
  templateUrl: './get-employee.html',
  styleUrls: ['./get-employee.css']
})
export class GetEmployee implements OnInit {
  employees: Employee[] = [];
  addresses: Address[]=[];

  constructor(
    private employeeService: EmployeeService,
    private addressService: AddressService  
  ) {}

  ngOnInit(): void {
    this.getAllEmployees();
    this.getAllAddresses();
  }

 getAllEmployees(): void {
  this.employeeService.getAllEmployees().subscribe(data => {
    this.employees = data;
    console.log('Employees:', this.employees);
  });
}
  getAllAddresses(): void {
    this.addressService.getAllAddress().subscribe(data => {
      this.addresses = data;
      console.log('Addresses:', this.addresses);
    });
  }
}