package com.coffee_bean.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "products")
@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private double price;
    private String image;
    private int stock;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "fk_category_id")
    private Category category;
}
