package com.viscovich.backend_store.customers.controller;

import com.viscovich.backend_store.customers.model.Customer;
import com.viscovich.backend_store.customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> allCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody Customer newCustomer){
        return service.saveCustomer(newCustomer);
    }
}
