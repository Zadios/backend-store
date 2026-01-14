package com.viscovich.backend_store.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viscovich.backend_store.customers.model.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDateTime date;

    private double total;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("orders")
    private Customer customer;
}
