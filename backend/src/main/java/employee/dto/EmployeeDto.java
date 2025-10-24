package employee.dto;
import employee.entity.Address;
import employee.entity.Laptop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Data
public class EmployeeDto {
    private int id;
    private String name;
    private String number;
    private String email;
    private Laptop laptop;
    private List<Address> addresses;
}
