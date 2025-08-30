package com.flink.flink_backend.controller;

import com.flink.flink_backend.dto.ProductDetailDto;
import com.flink.flink_backend.dto.PageResponse;
import com.flink.flink_backend.dto.ProductListItemDto;
import com.flink.flink_backend.service.ProductQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/** 상품 검색/상세 REST 컨트롤러 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductQueryService service;
    public ProductController(ProductQueryService service) { this.service = service; }

    /** GET /api/v1/products/search
     *  - query: 이름/브랜드 키워드(옵션)
     *  - page/size: 페이지네이션
     */
    @GetMapping("/products/search")
    public PageResponse<ProductListItemDto> search(
        @RequestParam(required = false) String query,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size) {
        return service.search(query, page, size);
    }

    /** GET /api/v1/products/{product_id} - 상품 상세 */
    @GetMapping("/products/{product_id}")
    public ProductDetailDto detail(@PathVariable("product_id") Long productId) {
        var dto = service.detail(productId);
        if (dto == null) {
            // 존재하지 않는 경우 404 반환
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
        return dto;
    }
}
