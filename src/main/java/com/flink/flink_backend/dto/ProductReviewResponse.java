package com.flink.flink_backend.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class ProductReviewResponse {
    private final Long reviewId;
    private final String userId;
    private final String vendorName;
    private final BigDecimal score;
    private final String content;
    private final LocalDate writtenDate;
    private final List<String> images;

    public ProductReviewResponse(Long reviewId, String userId, String vendorName, BigDecimal score, String content, LocalDate writtenDate, List<String> images) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.vendorName = vendorName;
        this.score = score;
        this.content = content;
        this.writtenDate = writtenDate;
        this.images = images;
    }
}
