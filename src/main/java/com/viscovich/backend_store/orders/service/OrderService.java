package com.viscovich.backend_store.orders.service;

import com.viscovich.backend_store.common.exception.ResourceNotFoundException;
import com.viscovich.backend_store.customers.model.Customer;
import com.viscovich.backend_store.customers.repository.CustomerRepository;
import com.viscovich.backend_store.orders.model.Order;
import com.viscovich.backend_store.products.model.Product;
import com.viscovich.backend_store.orders.repository.OrderRepository;
import com.viscovich.backend_store.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Long customerId, List<Long> productIds, Order order) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + customerId));

        List<Product> products = productRepository.findAllById(productIds);

        if(products.isEmpty()){
            throw new RuntimeException("Una órden debe contener al menos un producto válido.");
        }

        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        for (Product product : products) {
            if(product.getStock() < 1) {
                throw new RuntimeException("No hay stock suficiente del producto: " + product.getName());
            }
        }

        for (Product product : products) {
            product.setStock(product.getStock() - 1);
        }

        order.setCustomer(customer);
        order.setProducts(products);
        order.setTotal(total);
        order.setDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("No existe un cliente con el ID: " + customerId);
        }
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
