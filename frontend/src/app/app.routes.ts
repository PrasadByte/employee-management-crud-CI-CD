import { Routes } from '@angular/router';
import { GetEmployee } from './component/get-employee/get-employee';
import { Addemployee } from './component/addemployee/addemployee';

export const routes: Routes = [
    {path:'home', component:GetEmployee},
    {path:'create', component:Addemployee}

];
