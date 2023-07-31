package De.com.example.demoAPIJAVA.Repository;

import De.com.example.demoAPIJAVA.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT e FROM Customer e WHERE e.name = ?1 ")
    List<Customer> findAllOrderByNameDesc(String name);
}
