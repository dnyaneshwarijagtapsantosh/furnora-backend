package com.furnora.furnora_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    // Delivery ani payment chi mahiti (junya orders sathi null asel)
    private String customerName;
    private String phone;

    @Column(length = 500)
    private String address;

    private String paymentMethod;   // COD kinva ONLINE
    private String paymentStatus;   // PAY_ON_DELIVERY kinva PAID_DEMO

    // Order kevha pohochnar
    private LocalDate expectedDeliveryDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public enum Status {
        PENDING, SHIPPED, DELIVERED, CANCELLED
    }
}