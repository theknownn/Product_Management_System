package com.example.product.Controller;


import com.example.product.Model.Product;
import com.example.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    //To display the list of all Products.
    @GetMapping("/products")
    public List<Product> listAll(){
        return service.listAll();
    }

    //To Add new product in the database.
    @PostMapping("/add/product")
    public Product add(@RequestBody Product product){
        return service.save(product);
    }

    //To Get product by their id.
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> get(@PathVariable("id")int id){
        try {
            Product product= service.getById(id);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    //To Update the data of product
    @PutMapping("/put/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable ("id")int id) {
        try {
            Product existProduct = service.getById(id);
            service.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //To Delete the data of product
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")int id){
        service.delete(id);
    }









}
