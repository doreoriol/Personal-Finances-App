package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DashboardSummaryResponse {

    private BigDecimal totalBalance;
    private BigDecimal totalIncomeCurrentMonth;
    private BigDecimal totalExpenseCurrentMonth;
    private List<AccountResponse> accounts;
    private List<TransactionResponse> recentTransactions;
}
