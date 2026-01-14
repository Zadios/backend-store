package com.viscovich.backend_store.orders.controller;

import com.viscovich.backend_store.orders.model.Order;
import com.viscovich.backend_store.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> allOrders() {
        return service.getAllOrders();
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Order> create(@PathVariable Long customerId, @RequestBody Order order) {
        Order newOrder = service.createOrder(customerId, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
}
