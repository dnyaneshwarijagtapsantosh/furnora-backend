package com.furnora.furnora_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    private String imageUrl;

    // Furniture-specific fields
    private String material;   // e.g. Wood, Metal, Plastic
    private String dimensions; // e.g. "120x60x75 cm"
    private String color;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}