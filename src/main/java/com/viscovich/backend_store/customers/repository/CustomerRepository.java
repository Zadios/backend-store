package com.viscovich.backend_store.customers.repository;

import com.viscovich.backend_store.customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
