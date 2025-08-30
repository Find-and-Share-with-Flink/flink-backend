package com.flink.flink_backend.dto;

import java.util.List;

/** 페이지네이션 공통 응답 래퍼 */
public class PageResponse<T> {
    private final List<T> data;  // 현재 페이지 데이터
    private final int page;      // 현재 페이지 번호(1-base)
    private final int size;      // 페이지 크기
    private final long total;    // 전체 건수

    public PageResponse(List<T> data, int page, int size, long total) {
        this.data = data; this.page = page; this.size = size; this.total = total;
    }
    public List<T> getData() { return data; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public long getTotal() { return total; }
}
