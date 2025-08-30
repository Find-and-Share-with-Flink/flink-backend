package com.flink.flink_backend.dto;

/** 상품 목록의 한 아이템 DTO */
public class ProductListItemDto {
    private final Long product_id;      // 상품 ID
    private final String name;          // 상품명
    private final String brand;         // 브랜드명
    private final String thumbnail_url; // 썸네일 URL
    private final Double min_price;     // 최저가(가격 테이블 MIN)
    private final Integer review_count; // 리뷰 수
    private final Double avg_rating;    // 평균 평점

    public ProductListItemDto(Long product_id, String name, String brand, String thumbnail_url,
                              Double min_price, Integer review_count, Double avg_rating) {
        this.product_id = product_id; this.name = name; this.brand = brand;
        this.thumbnail_url = thumbnail_url; this.min_price = min_price;
        this.review_count = review_count; this.avg_rating = avg_rating;
    }
    public Long getProduct_id() { return product_id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getThumbnail_url() { return thumbnail_url; }
    public Double getMin_price() { return min_price; }
    public Integer getReview_count() { return review_count; }
    public Double getAvg_rating() { return avg_rating; }
}
