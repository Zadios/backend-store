package com.viscovich.backend_store.products.controller;

import com.viscovich.backend_store.products.model.Product;
import com.viscovich.backend_store.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> allProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product newProduct) {
        Product savedProduct = service.saveProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

}
