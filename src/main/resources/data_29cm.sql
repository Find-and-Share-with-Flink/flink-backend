-- 상품 (itemNo를 product_id로 사용)
INSERT INTO products (product_id, name, brand, description, thumbnail_url, category_id, created_at, updated_at)
VALUES (987654321,'트렌치 코트','CITYBREEZE','테스트 상세','https://img.example/987654321.jpg',1,NOW(),NOW())
    ON CONFLICT (product_id) DO UPDATE
                                    SET name=EXCLUDED.name, brand=EXCLUDED.brand, description=EXCLUDED.description, thumbnail_url=EXCLUDED.thumbnail_url, updated_at=NOW();

-- 이미지
INSERT INTO product_images (product_id, image_url, order_index) VALUES
                                                                    (987654321,'https://img.example/987654321_1.jpg',0),
                                                                    (987654321,'https://img.example/987654321_2.jpg',1)
    ON CONFLICT DO NOTHING;

-- 가격
INSERT INTO product_prices (product_id, vendor_id, price, shipping_fee, product_url, created_at)
VALUES (987654321,2,25900,28400,2500,'https://shop.29cm.co.kr/product/987654321',NOW());

-- 리뷰
INSERT INTO product_reviews (product_id, vendor_id, review_id, user_id, content, score, written_date, created_at)
VALUES (987654321,2,1001,'abc123','예뻐요',4.5,'2025-06-01',NOW())
    ON CONFLICT (product_id, vendor_id, review_id) DO UPDATE
                                                          SET content=EXCLUDED.content, score=EXCLUDED.score, written_date=EXCLUDED.written_date;

-- 리뷰 이미지
INSERT INTO review_images (product_review_id, image_url, order_index)
SELECT pr.product_review_id, 'https://img.example/rev1001_img1.jpg', 0
FROM product_reviews pr
WHERE pr.product_id=987654321 AND pr.vendor_id=2 AND pr.review_id=1001
    ON CONFLICT DO NOTHING;

-- 카테고리 연결
INSERT INTO product_categories (category_id, product_id)
SELECT cm.category_id, 987654321
FROM category_mappings cm
WHERE cm.platform='29cm' AND cm.external_type='middle' AND cm.external_id=268102100
    ON CONFLICT DO NOTHING;
