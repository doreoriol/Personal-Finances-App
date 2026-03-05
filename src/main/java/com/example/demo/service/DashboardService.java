package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardSummaryResponse;
import com.example.demo.dto.TransactionSummaryResponse;
import com.example.demo.dto.TransactionSummaryResponse.CategoryTotal;
import com.example.demo.enums.TransactionType;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class DashboardService {

    private final CurrentUserService currentUserService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public DashboardService(CurrentUserService currentUserService,
                            AccountRepository accountRepository,
                            TransactionRepository transactionRepository) {
        this.currentUserService = currentUserService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public DashboardSummaryResponse getDashboardSummary(Integer year, Integer month) {
        var user = currentUserService.getCurrentUser();
        long userId = user.getId();

        YearMonth ym = (year != null && month != null)
                ? YearMonth.of(year, month)
                : YearMonth.now();

        LocalDate from = ym.atDay(1);
        LocalDate to = ym.atEndOfMonth();

        List<Account> accounts = accountRepository.findByUserId(userId);
        List<Transaction> monthTransactions = transactionRepository.findByUserIdAndDateBetween(userId, from, to);
        List<Transaction> recentTransactions = transactionRepository.findTop5ByUserIdOrderByDateDesc(userId);

        BigDecimal totalBalance = accounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TransactionSummaryResponse summary = buildTransactionSummary(monthTransactions);

        DashboardSummaryResponse response = new DashboardSummaryResponse();
        response.setAccounts(accounts);
        response.setRecentTransactions(recentTransactions);
        response.setTotalBalance(totalBalance);
        response.setTotalIncomeCurrentMonth(summary.getTotalIncome());
        response.setTotalExpenseCurrentMonth(summary.getTotalExpense());

        return response;
    }

    public TransactionSummaryResponse getTransactionSummary(LocalDate from, LocalDate to) {
        var user = currentUserService.getCurrentUser();

        LocalDate start = from != null ? from : LocalDate.now().withDayOfMonth(1);
        LocalDate end = to != null ? to : LocalDate.now();

        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(user.getId(), start, end);
        return buildTransactionSummary(transactions);
    }

    private TransactionSummaryResponse buildTransactionSummary(List<Transaction> transactions) {
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        java.util.Map<Long, CategoryTotal> categoryMap = new java.util.HashMap<>();

        for (Transaction t : transactions) {
            BigDecimal amount = t.getAmount();
            if (t.getType() == TransactionType.INCOME) {
                totalIncome = totalIncome.add(amount);
            } else {
                totalExpense = totalExpense.add(amount);
            }

            Long categoryId = t.getCategory().getId();
            CategoryTotal current = categoryMap.get(categoryId);
            if (current == null) {
                current = new CategoryTotal(categoryId, t.getCategory().getName(), amount);
            } else {
                current = new CategoryTotal(categoryId, current.getCategoryName(), current.getTotal().add(amount));
            }
            categoryMap.put(categoryId, current);
        }

        TransactionSummaryResponse response = new TransactionSummaryResponse();
        response.setTotalIncome(totalIncome);
        response.setTotalExpense(totalExpense);
        response.setByCategory(new java.util.ArrayList<>(categoryMap.values()));

        return response;
    }
}

