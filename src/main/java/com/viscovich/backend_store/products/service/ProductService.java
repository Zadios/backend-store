package com.viscovich.backend_store.products.service;
import com.viscovich.backend_store.products.model.Product;
import com.viscovich.backend_store.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }
}
