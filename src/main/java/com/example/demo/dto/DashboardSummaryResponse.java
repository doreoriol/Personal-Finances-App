package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardSummaryResponse {

    private BigDecimal totalBalance;
    private BigDecimal totalIncomeCurrentMonth;
    private BigDecimal totalExpenseCurrentMonth;
    private List<AccountResponse> accounts;
    private List<TransactionResponse> recentTransactions;

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getTotalIncomeCurrentMonth() {
        return totalIncomeCurrentMonth;
    }

    public void setTotalIncomeCurrentMonth(BigDecimal totalIncomeCurrentMonth) {
        this.totalIncomeCurrentMonth = totalIncomeCurrentMonth;
    }

    public BigDecimal getTotalExpenseCurrentMonth() {
        return totalExpenseCurrentMonth;
    }

    public void setTotalExpenseCurrentMonth(BigDecimal totalExpenseCurrentMonth) {
        this.totalExpenseCurrentMonth = totalExpenseCurrentMonth;
    }

    public List<AccountResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResponse> accounts) {
        this.accounts = accounts;
    }

    public List<TransactionResponse> getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(List<TransactionResponse> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }
}

