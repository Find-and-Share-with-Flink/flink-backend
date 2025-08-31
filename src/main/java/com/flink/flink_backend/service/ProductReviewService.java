package com.flink.flink_backend.service;

import com.flink.flink_backend.dto.ProductReviewResponse;
import com.flink.flink_backend.entity.ProductReview;
import com.flink.flink_backend.entity.ReviewImage;
import com.flink.flink_backend.repository.ProductRepository;
import com.flink.flink_backend.repository.ProductReviewRepository;
import com.flink.flink_backend.repository.ReviewImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ProductRepository productRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository,
                                ReviewImageRepository reviewImageRepository,
                                ProductRepository productRepository) {
        this.productReviewRepository = productReviewRepository;
        this.reviewImageRepository = reviewImageRepository;
        this.productRepository = productRepository;
    }

    public Page<ProductReviewResponse> getReviews(Long productId, int page, int size) {
        productRepository.findById(productId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Page<Long> reviewPage = productReviewRepository.findReviewIdsByProductId(
            productId, PageRequest.of(page - 1, size)
        );

        List<Long> reviewIds = reviewPage.getContent();
        if (reviewIds.isEmpty()) {
            return Page.empty();
        }

        List<ProductReview> reviews = productReviewRepository.findAllWithVendorByIds(reviewIds);

        List<ReviewImage> images = reviewImageRepository
            .findByProductReview_ProductReviewIdInOrderByOrderIndexAsc(reviewIds);

        List<ProductReviewResponse> responseList = reviews.stream().map(review -> {
            List<String> imageUrls = images.stream()
                .filter(img -> img.getProductReview().getProductReviewId().equals(review.getProductReviewId()))
                .map(ReviewImage::getImageUrl)
                .toList();

            return new ProductReviewResponse(
                review.getProductReviewId(),
                review.getUserId(),
                review.getVendor().getName(),
                review.getScore(),
                review.getContent(),
                review.getWrittenDate(),
                imageUrls
            );
        }).toList();

        return new PageImpl<>(
            responseList,
            PageRequest.of(page - 1, size),
            reviewPage.getTotalElements()
        );
    }
}
