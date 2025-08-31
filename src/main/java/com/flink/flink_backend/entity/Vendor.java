package com.flink.flink_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vendors")
@Getter
@Setter
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(nullable = false, length = 100)
    private String name;

    private BigDecimal rating;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<ProductReview> reviews;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<ProductPrice> prices;
}
