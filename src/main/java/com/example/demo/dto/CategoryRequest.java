package com.example.demo.dto;

import com.example.demo.enums.CategoryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequest {

    @NotBlank
    private String name;

    @NotNull
    private CategoryType type;
}
