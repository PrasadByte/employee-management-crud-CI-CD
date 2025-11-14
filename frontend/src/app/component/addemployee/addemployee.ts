import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-addemployee',
  imports: [ReactiveFormsModule, CommonModule], // ✅ Import modules needed for reactive forms & common directives
  templateUrl: './addemployee.html', // ✅ Template file (HTML form)
  styleUrl: './addemployee.css'      // ✅ CSS file for styling
})
export class Addemployee implements OnInit {

  employeeForm!: FormGroup;  

  constructor(private fb: FormBuilder) {} 

  ngOnInit(): void {
    
    this.employeeForm = this.fb.group({
      id: [null, Validators.required],        // Employee ID (required)
      username: ['', Validators.required],    // Username (required)
      password: ['', Validators.required],    // Password (required)
      name: ['', Validators.required],        // Name (required)
      department: ['', Validators.required],  // Department (required)
      salary: [null, Validators.required],    // Salary (required)

      // 👇 Addresses is a FormArray → can hold multiple address groups
      addresses: this.fb.array([this.createAddressGroup()])
    });
  }

  // ✅ Create one address group (used inside addresses array)
  createAddressGroup(): FormGroup {
    return this.fb.group({
      id: [null],                           // Address ID
      city: ['', Validators.required],      // City (required)
      pinCode: [null, Validators.required]  // Pin Code (required)
    });
  }
 

  // ✅ Getter → easy way to access the addresses FormArray
  get addresses(): FormArray {
    return this.employeeForm.get('addresses') as FormArray;
  }

  // ✅ Add a new address to the FormArray
  addAddress(): void {
    this.addresses.push(this.createAddressGroup());
  }

  // ✅ Remove an address by index (ex: remove 2nd address)
  removeAddress(index: number): void {
    this.addresses.removeAt(index);
  }

  // ✅ On form submit → print all entered employee data in console
  onSubmit(): void {
    console.log(this.employeeForm.value);
  }

}
