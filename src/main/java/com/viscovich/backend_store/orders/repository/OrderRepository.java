package com.viscovich.backend_store.products.repository;
import com.viscovich.backend_store.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

