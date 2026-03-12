package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetResponse {

    private Long id;
    private YearMonth month;
    private BigDecimal limitAmount;
    private BigDecimal spentAmount;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
