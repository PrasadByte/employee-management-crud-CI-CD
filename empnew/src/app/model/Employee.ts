import { Address } from "./address";


export interface Employee {
  id: number;
  username: string;
  password: string;
  name: string;
  department: string;
  salary: number;
  addresses: Address[];

}
