package com.postgresql.demo.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class GameDtos {

    // CREATE
    public static class GameCreateRequest {
        @NotBlank public String name;
        @Size(max=1000) public String description;
        public String image;
        @NotBlank public String category;
        @PositiveOrZero public BigDecimal price;
    }

    // UPDATE
    public static class GameUpdateRequest {
        public String name;
        public String description;
        public String image;
        public String category;
        @PositiveOrZero public BigDecimal price;
    }

    // OUTPUT
    public static class GameResponse{
        public Long id;
        public String name;
        public String description;
        public String image;
        public String category;
        public BigDecimal price;
        public BigDecimal finalPrice;

        public GameResponse(Long id, String name, String description, String image,
                            String category, BigDecimal price, BigDecimal finalPrice) {
            this.id = id; this.name = name; this.description = description;
            this.image = image; this.category = category; this.price = price; this.finalPrice = finalPrice;
        }
    }

    // Wrapper pagination
    public static class PageResponse<T> {
        public java.util.List<T> items;
        public int page;
        public int size;
        public long totalElements;
        public int totalPages;

        public PageResponse(java.util.List<T> items, int page, int size, long totalElements, int totalPages) {
            this.items = items; this.page = page; this.size = size;
            this.totalElements = totalElements; this.totalPages = totalPages;
        }
    }

}
