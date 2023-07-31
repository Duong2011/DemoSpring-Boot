package De.com.example.demoAPIJAVA.Repository;

import De.com.example.demoAPIJAVA.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    /*List<Product> findByProductName(String Name);*/

    @Query("SELECT e FROM Product e WHERE e.ProductName = ?1 ")
    List<Product> findAllOrderByNameDesc(String name);

    @Query("SELECT e FROM Product e WHERE e.Price <=30000 ")
    List<Product> findAllProductByPrice();
}
