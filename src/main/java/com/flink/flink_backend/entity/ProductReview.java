package com.flink.flink_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product_reviews",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id","vendor_id","review_id"}))
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    private Long productReviewId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_id", columnDefinition = "TEXT")
    private String userId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Float score;

    @Column(name = "written_date")
    private LocalDate writtenDate;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL)
    private List<ReviewImage> images;

    // getters & setters
}
