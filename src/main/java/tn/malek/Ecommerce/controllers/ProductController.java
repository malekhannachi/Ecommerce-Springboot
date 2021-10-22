package tn.malek.Ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.malek.Ecommerce.models.Product;
import tn.malek.Ecommerce.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostMapping(path = "add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        try {
            Product product = this.productRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product());
        }
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String ,String>> deleteCategory(@PathVariable Integer id) {
        this.productRepository.deleteById(id);

        HashMap<String,String> response = new HashMap<>();
        response.put("message","product deleted");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping(path = "update")
    public ResponseEntity<Product> updateCategory(@RequestBody Product product) {
        Product updatedProduct = this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }



}
