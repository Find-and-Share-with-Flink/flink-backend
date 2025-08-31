package com.flink.flink_backend.controller;

import com.flink.flink_backend.dto.PageResponse;
import com.flink.flink_backend.dto.ProductReviewResponse;
import com.flink.flink_backend.service.ProductReviewService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/{productId}/reviews")
    public PageResponse<ProductReviewResponse> getProductReviews(
        @PathVariable("productId") Long productId,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        Page<ProductReviewResponse> reviewPage = productReviewService.getReviews(productId, page, size);

        return new PageResponse<>(
                reviewPage.getContent(),
                page,
                size,
                reviewPage.getTotalElements()
        );
    }
}
