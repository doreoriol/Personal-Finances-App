package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.BudgetResponse;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.dto.UserProfileResponse;
import com.example.demo.model.Account;
import com.example.demo.model.Budget;
import com.example.demo.model.Category;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;

@Component
public class ResponseMapper {

    public AccountResponse toAccountResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setName(account.getName());
        response.setType(account.getType());
        response.setBalance(account.getBalance());
        response.setCurrency(account.getCurrency());
        response.setCreatedAt(account.getCreatedAt());
        response.setUpdatedAt(account.getUpdatedAt());
        return response;
    }

    public CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setType(category.getType());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        return response;
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setType(transaction.getType());
        response.setAmount(transaction.getAmount());
        response.setDescription(transaction.getDescription());
        response.setDate(transaction.getDate());
        response.setPaymentMethod(transaction.getPaymentMethod());
        response.setCreatedAt(transaction.getCreatedAt());
        response.setUpdatedAt(transaction.getUpdatedAt());
        response.setCategoryId(transaction.getCategory().getId());
        response.setCategoryName(transaction.getCategory().getName());
        return response;
    }

    public BudgetResponse toBudgetResponse(Budget budget) {
        BudgetResponse response = new BudgetResponse();
        response.setId(budget.getId());
        response.setMonth(budget.getMonth());
        response.setLimitAmount(budget.getLimitAmount());
        response.setSpentAmount(budget.getSpentAmount());
        response.setCreatedAt(budget.getCreatedAt());
        response.setUpdatedAt(budget.getUpdatedAt());
        response.setCategoryId(budget.getCategory().getId());
        response.setCategoryName(budget.getCategory().getName());
        return response;
    }

    public UserProfileResponse toUserProfileResponse(User user) {
        UserProfileResponse response = new UserProfileResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
