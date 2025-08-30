package com.flink.flink_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

/** 상품 상세 DTO (이미지/최저가/리뷰집계 포함) */
public class ProductDetailDto {
    private Long product_id;           // 상품 ID
    private String name;               // 상품명
    private String brand;              // 브랜드명
    private List<ImageDto> images;     // 이미지 리스트(정렬 포함)
    private Double min_price;          // 최저가
    private Integer review_count;      // 리뷰 수
    private Double avg_rating;         // 평균 평점
    private LocalDateTime created_at;  // 생성 시각

    /** 상세 이미지 DTO (순서값 포함) */
    public static class ImageDto {
        public String image_url;
        public Integer order_index;
        public ImageDto(String url, Integer idx) { this.image_url = url; this.order_index = idx; }
    }

    // getter/setter
    public Long getProduct_id(){ return product_id; }
    public void setProduct_id(Long v){ this.product_id = v; }
    public String getName(){ return name; }
    public void setName(String v){ this.name = v; }
    public String getBrand(){ return brand; }
    public void setBrand(String v){ this.brand = v; }
    public List<ImageDto> getImages(){ return images; }
    public void setImages(List<ImageDto> v){ this.images = v; }
    public Double getMin_price(){ return min_price; }
    public void setMin_price(Double v){ this.min_price = v; }
    public Integer getReview_count(){ return review_count; }
    public void setReview_count(Integer v){ this.review_count = v; }
    public Double getAvg_rating(){ return avg_rating; }
    public void setAvg_rating(Double v){ this.avg_rating = v; }
    public LocalDateTime getCreated_at(){ return created_at; }
    public void setCreated_at(LocalDateTime v){ this.created_at = v; }
}
