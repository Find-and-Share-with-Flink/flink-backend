package com.flink.flink_backend.repository;

import com.flink.flink_backend.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
    List<ReviewImage> findByProductReview_ProductReviewIdInOrderByOrderIndexAsc(List<Long> ids);
}
