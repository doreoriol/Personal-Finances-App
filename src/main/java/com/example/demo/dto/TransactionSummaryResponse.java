package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class TransactionSummaryResponse {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private List<CategoryTotal> byCategory;

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public List<CategoryTotal> getByCategory() {
        return byCategory;
    }

    public void setByCategory(List<CategoryTotal> byCategory) {
        this.byCategory = byCategory;
    }

    public static class CategoryTotal {
        private Long categoryId;
        private String categoryName;
        private BigDecimal total;

        public CategoryTotal(Long categoryId, String categoryName, BigDecimal total) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.total = total;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public BigDecimal getTotal() {
            return total;
        }
    }
}

