package com.flink.flink_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "icon_url", length = 2048)
    private String iconUrl;

    @Column(name = "parent_id")
    private Long parentId;
}
