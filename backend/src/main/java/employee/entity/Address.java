package employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
@Entity
@Data
@Table(name = "Employee_Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="employee_city")
    private String city;
    private String state;
    private int zipCode;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    @ToString.Exclude
    @JsonManagedReference
    private Employee employee;
}
