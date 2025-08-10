package com.flink.flink_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_prices")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "product_url", length = 2048)
    private String productUrl;

    private LocalDateTime createdAt;
}
