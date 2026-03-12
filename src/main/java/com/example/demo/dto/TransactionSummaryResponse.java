package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionSummaryResponse {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private List<CategoryTotal> byCategory;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryTotal {
        private Long categoryId;
        private String categoryName;
        private BigDecimal total;
    }
}
