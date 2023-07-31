package De.com.example.demoAPIJAVA.controllers;

import De.com.example.demoAPIJAVA.Repository.CustomerRepository;
import De.com.example.demoAPIJAVA.models.Customer;
import De.com.example.demoAPIJAVA.models.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Customer")
public class CustomerController {
    @Autowired
    CustomerRepository Customerrepository;
    @GetMapping("/all")
    List<Customer> getAllCustomer()
    {
        return Customerrepository.findAll();
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is exception");
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertCustomer(@RequestBody Customer newCustomer) {
        List<Customer> foundCustomer = Customerrepository.findAllOrderByNameDesc(newCustomer.getName().trim());
        if(foundCustomer.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Customer name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Customer successfully", Customerrepository.save(newCustomer))
        );
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)
    {
        Optional<Customer> foundCustomer  = Customerrepository.findById(id);
        return foundCustomer.isPresent()?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK","Query Customer successfully",foundCustomer))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("False","Cannot find Customer by id :"+id,"")
        );
    }
}
