package De.com.example.demoAPIJAVA.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblProduct")
public class Product {
    @Id
   /* @GeneratedValue(strategy=GenerationType.AUTO)*/
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long ProductID;
    @Column(nullable = false,length = 255,unique = true)
    private String ProductName;
    private float Price;
    private String url;


    @Override
    public String toString() {
        return "Product{" +
                "ProductID=" + ProductID +
                ", ProductName='" + ProductName + '\'' +
                ", Price=" + Price +
                ", url='" + url + '\'' +
                '}';
    }

}
