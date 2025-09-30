import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { Address } from '../model/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
 constructor(private http: HttpClient
 ){}
private addurl = `${environment.Base_url}/api/address`;

getAllAddress(): Observable<Address[]>{
  return this.http.get<Address[]>(`${this.addurl}/all`)
}
}