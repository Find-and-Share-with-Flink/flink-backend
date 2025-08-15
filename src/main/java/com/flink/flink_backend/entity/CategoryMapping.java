package com.flink.flink_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_mappings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"platform","external_type","external_id"}))
public class CategoryMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long mappingId;

    @Column(nullable = false, length = 50)
    private String platform;          // "29cm"

    @Column(name = "external_type", nullable = false, length = 20) // "middle" | "large"
    private String externalType;

    @Column(name = "external_id", nullable = false)
    private Long externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}