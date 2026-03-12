package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.enums.CategoryType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {

    private long id;
    private String name;
    private CategoryType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
