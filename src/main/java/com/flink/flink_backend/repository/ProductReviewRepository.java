package com.flink.flink_backend.repository;

import com.flink.flink_backend.entity.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {

    @Query(value = "select r.productReviewId from ProductReview r where r.product.productId = :productId order by r.writtenDate desc",
        countQuery = "select count(r) from ProductReview r where r.product.productId = :productId")
    Page<Long> findReviewIdsByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query("select r from ProductReview r " +
        "left join fetch r.vendor " +
        "where r.productReviewId in :ids " +
        "order by r.writtenDate desc")
    List<ProductReview> findAllWithVendorByIds(@Param("ids") List<Long> ids);
}
