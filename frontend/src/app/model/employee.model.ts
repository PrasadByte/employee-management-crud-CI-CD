import { Address } from "./address.model";


export interface Employee {
  id: number;
  name: string;
  mobileNo: string;
  email: string;
  password: string;
  department: string;
  salary: number;
  addresses: Address[];
  

}
