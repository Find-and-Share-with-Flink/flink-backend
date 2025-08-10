package com.flink.flink_backend.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductCategoryId implements Serializable {
    private Long categoryId;
    private Long productId;

    public ProductCategoryId() {}

    public ProductCategoryId(Long categoryId, Long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategoryId)) return false;
        ProductCategoryId that = (ProductCategoryId) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, productId);
    }
}
