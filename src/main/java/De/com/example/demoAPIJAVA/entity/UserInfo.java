package De.com.example.demoAPIJAVA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserInfo {
    @Id
    @SequenceGenerator(
        name = "userInfo_sequence",
        sequenceName = "userInfo_sequence",
        allocationSize = 1 // increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userInfo_sequence"
    )
/*  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)*/
    private Long id;
    private String name;
    private String email;
    private String password;
    private String roles;

}
