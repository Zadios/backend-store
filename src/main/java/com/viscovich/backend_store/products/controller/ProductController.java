package com.viscovich.backend_store.products.controller;

import com.viscovich.backend_store.products.model.Product;
import com.viscovich.backend_store.products.service.ProductService;
import jakarta.validation.Valid;
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
    List<Product> allProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    Product newProduct(@Valid @RequestBody Product newProduct){
        return service.saveProduct(newProduct);
    }

}
