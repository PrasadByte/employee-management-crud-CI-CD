package employee.service;


import employee.entity.Laptop;

import java.util.List;

public interface LaptopService {
//create new laptops
    public Laptop createNewLaptop(Laptop lapy);

    //get all Laptops
    List<Laptop> getAllLaptops();

}
