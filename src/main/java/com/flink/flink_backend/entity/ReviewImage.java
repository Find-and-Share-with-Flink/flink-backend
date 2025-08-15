package com.flink.flink_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review_images",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_review_id","image_url"}))
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "product_review_id", nullable = false)
    private ProductReview productReview;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "order_index")
    private Integer orderIndex;
}
