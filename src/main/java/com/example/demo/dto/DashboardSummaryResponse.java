package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;

public class DashboardSummaryResponse {

    private BigDecimal totalBalance;
    private BigDecimal totalIncomeCurrentMonth;
    private BigDecimal totalExpenseCurrentMonth;
    private List<Account> accounts;
    private List<Transaction> recentTransactions;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(List<Transaction> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }
}

