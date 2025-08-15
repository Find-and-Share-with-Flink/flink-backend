-- ========================
-- 1. 카테고리 (categories)
-- ========================
CREATE TABLE IF NOT EXISTS categories (
                                          category_id BIGSERIAL PRIMARY KEY,
                                          name        VARCHAR(255) NOT NULL,
    icon_url    VARCHAR(2048),
    parent_id   BIGINT
    );

-- ========================
-- 2. 상품 (products)
-- ========================
CREATE TABLE IF NOT EXISTS products (
                                        product_id    BIGINT PRIMARY KEY,
                                        category_id   BIGINT,
                                        name          VARCHAR(255) NOT NULL,
    brand         VARCHAR(255),
    description   TEXT,
    thumbnail_url VARCHAR(2048),
    created_at    TIMESTAMP DEFAULT NOW(),
    updated_at    TIMESTAMP DEFAULT NOW()
    );

-- ========================
-- 3. 상품 이미지 (product_images)
-- ========================
CREATE TABLE IF NOT EXISTS product_images (
                                              image_id    BIGSERIAL PRIMARY KEY,
                                              product_id  BIGINT NOT NULL REFERENCES products(product_id) ON DELETE CASCADE,
    image_url   VARCHAR(2048),
    order_index INT
    );

-- ========================
-- 4. 벤더 (vendors)
-- ========================
CREATE TABLE IF NOT EXISTS vendors (
                                       vendor_id BIGSERIAL PRIMARY KEY,
                                       name      VARCHAR(100) NOT NULL,
    rating    NUMERIC(2,1)
    );

-- ========================
-- 5. 상품 가격 (product_prices)
-- ========================
CREATE TABLE IF NOT EXISTS product_prices (
                                              price_id     BIGSERIAL PRIMARY KEY,
                                              product_id   BIGINT NOT NULL REFERENCES products(product_id) ON DELETE CASCADE,
    vendor_id    BIGINT NOT NULL REFERENCES vendors(vendor_id),
    price        NUMERIC(12,2) NOT NULL,
    shipping_fee NUMERIC(12,2),
    total_price  NUMERIC(12,2) GENERATED ALWAYS AS (COALESCE(price,0) + COALESCE(shipping_fee,0)) STORED,
    product_url  VARCHAR(2048),
    created_at   TIMESTAMP DEFAULT NOW()
    );

-- ========================
-- 6. 상품 리뷰 (product_reviews)
-- ========================
CREATE TABLE IF NOT EXISTS product_reviews (
                                               product_review_id BIGSERIAL PRIMARY KEY,
                                               product_id        BIGINT NOT NULL REFERENCES products(product_id) ON DELETE CASCADE,
    vendor_id         BIGINT NOT NULL REFERENCES vendors(vendor_id),
    review_id         BIGINT,
    user_id           TEXT,
    content           TEXT,
    score             NUMERIC(2,1),
    written_date      DATE,
    created_at        TIMESTAMP DEFAULT NOW(),
    UNIQUE (product_id, vendor_id, review_id)
    );

-- ========================
-- 7. 리뷰 이미지 (review_images)
-- ========================
CREATE TABLE IF NOT EXISTS review_images (
                                             image_id          BIGSERIAL PRIMARY KEY,
                                             product_review_id BIGINT NOT NULL REFERENCES product_reviews(product_review_id) ON DELETE CASCADE,
    image_url         VARCHAR(2048),
    order_index       INT
    );

-- ========================
-- 8. 상품-카테고리 연결 (product_categories)
-- ========================
CREATE TABLE IF NOT EXISTS product_categories (
                                                  category_id BIGINT NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE,
    product_id  BIGINT NOT NULL REFERENCES products(product_id) ON DELETE CASCADE,
    PRIMARY KEY (category_id, product_id)
    );

-- ========================
-- 9. 외부 카테고리 매핑 (category_mappings)
-- ========================
CREATE TABLE IF NOT EXISTS category_mappings (
                                                 mapping_id     BIGSERIAL PRIMARY KEY,
                                                 platform       VARCHAR(50) NOT NULL,    -- 예: '29cm', 'wconcept'
    external_type  VARCHAR(20) NOT NULL,    -- 'middle' | 'large' 등
    external_id    BIGINT NOT NULL,
    category_id    BIGINT NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE,
    UNIQUE(platform, external_type, external_id)
    );

-- ========================
-- 인덱스 & 제약 조건
-- ========================
-- 이미지 중복 방지
CREATE UNIQUE INDEX IF NOT EXISTS uq_product_image
    ON product_images (product_id, image_url);

CREATE UNIQUE INDEX IF NOT EXISTS uq_review_image
    ON review_images (product_review_id, image_url);

-- 조회 성능 인덱스
CREATE INDEX IF NOT EXISTS idx_product_images_product
    ON product_images (product_id);

CREATE INDEX IF NOT EXISTS idx_product_prices_prod
    ON product_prices (product_id);

CREATE INDEX IF NOT EXISTS idx_product_prices_vendor
    ON product_prices (vendor_id);

CREATE INDEX IF NOT EXISTS idx_reviews_product
    ON product_reviews (product_id);

CREATE INDEX IF NOT EXISTS idx_review_images_review
    ON review_images (product_review_id);

CREATE INDEX IF NOT EXISTS idx_pc_product
    ON product_categories (product_id);
