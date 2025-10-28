import { Address } from "./address.model";
import { Laptops } from "./laptop.mode";


export interface Employee {
  id: number;
  name: string;
  mobileNo: string;
  email: string;
  laptop: Laptops;
  password: string;
  department: string;
  salary: number;
  addresses: Address[];
  

}
