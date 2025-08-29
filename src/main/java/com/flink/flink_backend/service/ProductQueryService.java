package com.flink.flink_backend.service;

import com.flink.flink_backend.dto.PageResponse;
import com.flink.flink_backend.dto.ProductListItemDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/** 상품 조회용 쿼리 서비스 */
@Service
public class ProductQueryService {
    private final NamedParameterJdbcTemplate jdbc;

    public ProductQueryService(NamedParameterJdbcTemplate jdbc) { this.jdbc = jdbc; }

    /** 상품 목록 검색 (이름/브랜드 LIKE, 최저가/리뷰집계 JOIN) */
    public PageResponse<ProductListItemDto> search(String query, int page, int size) {
        int offset = Math.max(0, (page - 1) * size);
        var params = new MapSqlParameterSource()
            .addValue("query", (query == null || query.isBlank()) ? null : "%" + query + "%")
            .addValue("limit", size).addValue("offset", offset);

        // 전체 개수 조회
        long total = jdbc.queryForObject("""
      SELECT COUNT(*) FROM products p
      WHERE (:query IS NULL OR p.name ILIKE :query OR p.brand ILIKE :query)
    """, params, Long.class);

        // 페이지 데이터 + 가격/리뷰 집계
        var rows = jdbc.query("""
      WITH base AS (
        SELECT p.product_id, p.name, p.brand, p.thumbnail_url
        FROM products p
        WHERE (:query IS NULL OR p.name ILIKE :query OR p.brand ILIKE :query)
        ORDER BY p.product_id DESC
        OFFSET :offset LIMIT :limit
      ),
      price AS ( SELECT product_id, MIN(total_price) AS min_price FROM product_prices GROUP BY product_id ),
      review AS ( SELECT product_id, COUNT(*) AS review_count, ROUND(AVG(score)::numeric,1) AS avg_rating
                  FROM product_reviews GROUP BY product_id )
      SELECT b.product_id, b.name, b.brand, b.thumbnail_url,
             COALESCE(pr.min_price,0) AS min_price,
             COALESCE(rv.review_count,0) AS review_count,
             COALESCE(rv.avg_rating,0) AS avg_rating
      FROM base b
      LEFT JOIN price pr ON pr.product_id = b.product_id
      LEFT JOIN review rv ON rv.product_id = b.product_id
    """, params, (rs, i) -> new ProductListItemDto(
            rs.getLong("product_id"),
            rs.getString("name"),
            rs.getString("brand"),
            rs.getString("thumbnail_url"),
            rs.getDouble("min_price"),
            rs.getInt("review_count"),
            rs.getDouble("avg_rating")
        ));

        return new PageResponse<>(rows, page, size, total);
    }
}
