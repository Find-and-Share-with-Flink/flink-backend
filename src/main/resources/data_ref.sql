-- 카테고리 5종
INSERT INTO categories (category_id, name, parent_id) VALUES
                                                          (1,'아우터',NULL),
                                                          (2,'상의',NULL),
                                                          (3,'하의',NULL),
                                                          (4,'드레스',NULL),
                                                          (5,'기타',NULL)
    ON CONFLICT (category_id) DO NOTHING;

-- 29CM 외부ID → 내부 카테고리 매핑
INSERT INTO category_mappings (platform, external_type, external_id, category_id) VALUES
                                                                                      ('29cm','middle',268102100,1),
                                                                                      ('29cm','middle',268103100,2),('29cm','middle',268105100,2),
                                                                                      ('29cm','middle',268106100,3),('29cm','middle',268107100,3),
                                                                                      ('29cm','middle',268104100,4),
                                                                                      ('29cm','middle',268116100,5),
                                                                                      ('29cm','large',271100100,5),('29cm','large',269100100,5),('29cm','large',305100100,5)
    ON CONFLICT (platform, external_type, external_id)
DO UPDATE SET category_id=EXCLUDED.category_id;

-- 쇼핑몰
INSERT INTO vendors (vendor_id, name, rating)
VALUES (1,'WCONCEPT',4.5),
       (2,'29CM',4.5)
    ON CONFLICT (vendor_id) DO UPDATE SET name=EXCLUDED.name, rating=EXCLUDED.rating;
