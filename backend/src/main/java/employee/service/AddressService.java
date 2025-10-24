package employee.service;
import employee.entity.Address;
import java.util.List;


public interface AddressService {

    //create address
    Address createAddress(Address address);

    //get all addresses
    List<Address>getAllAddresses();
    //get address by
}
