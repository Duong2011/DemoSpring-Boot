package De.com.example.demoAPIJAVA.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "Customer")
public class Customer {
    @Id
    /* @GeneratedValue(strategy=GenerationType.AUTO)*/
    @SequenceGenerator(
            name = "Customer_sequence",
            sequenceName = "Customer_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Customer_sequence"
    )
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
}
