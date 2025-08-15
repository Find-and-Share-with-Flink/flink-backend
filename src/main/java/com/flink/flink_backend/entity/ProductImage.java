package com.flink.flink_backend.entity;

import jakarta.persistence.*;

@Entity
//N+1 쿼리/과다 로딩 이슈로 인해서 모든 @ManyToOne을 LAZY 로딩으로 전환
@Table(name = "product_images",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id","image_url"}))
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "order_index")
    private Integer orderIndex;
}
