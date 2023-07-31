package De.com.example.demoAPIJAVA.controllers;

import De.com.example.demoAPIJAVA.Repository.ProductRepository;
import De.com.example.demoAPIJAVA.models.Product;
import De.com.example.demoAPIJAVA.models.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Products")
    public class ProductController {
    @Autowired
    private ProductRepository repository;
    // using thymeleaf return html example
    @GetMapping("/index")
    public ModelAndView getAllProduct() {
        List<Product> listproduct =repository.findAll();
        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("products",listproduct);
        modelAndView.getModel();
        return modelAndView;
    }
    // get all
    @GetMapping("/all")
    public List<Product> GetAllProduct(){
        return repository.findAll();
    }

    // example CRUD
    // get detail product by id
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)
    {
        Optional<Product> foundProduct  = repository.findById(id);
        return foundProduct.isPresent()?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK","Query product successfully",foundProduct))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("False","Cannot find product by id :"+id,"")
        );
    }
    @GetMapping("price")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Product> getallproductbyprice()
    {
        return repository.findAllProductByPrice();
    }
    // insert product
     @PostMapping("insertProduct")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
            //2 products must not have the same name !
           // using query find product by name
            List<Product> foundProducts = repository.findAllOrderByNameDesc(newProduct.getProductName().trim());
            if(foundProducts.size() > 0) {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Product name already taken", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct))
            );
        }
        //update, upsert = update if found, otherwise insert by id
   @PutMapping("/{id}")
   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
   ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
       Product updatedProduct = repository.findById(id)
               .map(Product -> {
                   Product.setProductName(newProduct.getProductName());
                   Product.setPrice(newProduct.getPrice());
                   return repository.save(Product);
               }).orElseGet(() -> {
                   newProduct.setProductID(id);
                   return repository.save(newProduct);
               });
       return ResponseEntity.status(HttpStatus.OK).body(
               new ResponseObject("ok", "Update Product successfully", updatedProduct)
       );
   }
   //Delete product by id
   @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
   ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
       boolean exists = repository.existsById(id);
       if(exists) {
           repository.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("ok", "Delete product successfully", "")
           );
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
               new ResponseObject("failed", "Cannot find product to delete", "")
       );
   }
}
