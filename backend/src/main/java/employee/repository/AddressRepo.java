package employee.repository;

import employee.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    default int add(int a , int b) {
        // Custom method implementation
        return a + b;

    }

}
