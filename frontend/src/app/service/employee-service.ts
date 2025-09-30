import { Injectable } from '@angular/core';
import { Employee } from '../model/Employee';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

 // private empUrl = `${environment.Base_url}/api/v1/emp`;
  //private empUrl = environment.employeeApiUrl;
  private empUrl = `${environment.Base_url}/api/v1/emp`;

  //private empUrl = 'http://localhost:8181/api/v1/emp'
  constructor(private http:HttpClient) {}

   getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.empUrl}/all`);
  }



  
}
