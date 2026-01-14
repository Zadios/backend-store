package com.viscovich.backend_store.orders.service;

import com.viscovich.backend_store.common.exception.ResourceNotFoundException;
import com.viscovich.backend_store.customers.model.Customer;
import com.viscovich.backend_store.customers.repository.CustomerRepository;
import com.viscovich.backend_store.orders.model.Order;
import com.viscovich.backend_store.products.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Long customerId, Order order) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + customerId));

        System.out.println("Cliente encontrado: " + customer.getFirstName());

        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
