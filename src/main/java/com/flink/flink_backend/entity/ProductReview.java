package com.flink.flink_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
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

    /*
    ProductReview.score 타입과 DB 스키마의 컬럼 타입이 달라서 오류 발생했음
    엔티티의 Float를 DB의 NUMERIC(2,1)에 맞춰 BigDecimal로 변경 진행
    (수정 전)
    private Float score;
    */
    @Column(precision = 2, scale = 1)
    private BigDecimal score;

    @Column(name = "written_date")
    private LocalDate writtenDate;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL)
    private List<ReviewImage> images;

    // getters & setters
}
