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

public class CustomerController {
    CustomerRepository repository;

    final private List<Customer> customers = List.of(
            Customer.builder().name("Customer 1").email("c1@gmail.com").build(),
            Customer.builder().name("Customer 2").email("c2@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is exception");
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertCustomer(@RequestBody Customer newCustomer) {
        List<Customer> foundCustomer = repository.findAllOrderByNameDesc(newCustomer.getName().trim());
        if(foundCustomer.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Customer name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Customer successfully", repository.save(newCustomer))
        );
    }

    @GetMapping("customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> customers = this.customers;
        return ResponseEntity.ok(customers);
    }


    @GetMapping("customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)
    {
        Optional<Customer> foundCustomer  = repository.findById(id);
        return foundCustomer.isPresent()?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK","Query Customer successfully",foundCustomer))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("False","Cannot find Customer by id :"+id,"")
        );
    }
}
