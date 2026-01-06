package com.viscovich.backend_store.products.repository;
import com.viscovich.backend_store.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
