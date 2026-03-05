package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetRequest {
    
    @NotNull
    private YearMonth month;

    @NotNull
    @Positive
    private BigDecimal limitAmount;

    @NotNull
    @PositiveOrZero
    private BigDecimal spentAmount;

    @NotNull
    private Long categoryId;
}
