package com.flink.flink_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_categories")
@IdClass(ProductCategoryId.class)
public class ProductCategory {

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Id
    @Column(name = "product_id")
    private Long productId;
}
