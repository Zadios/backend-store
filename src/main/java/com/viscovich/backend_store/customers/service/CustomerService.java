package com.viscovich.backend_store.customers.service;

import com.viscovich.backend_store.common.exception.EmailAlreadyExistsException;
import com.viscovich.backend_store.customers.model.Customer;
import com.viscovich.backend_store.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        if (repository.existsByEmail(customer.getEmail())) {
            throw new EmailAlreadyExistsException(customer.getEmail());
        }
        return repository.save(customer);
    }
}
