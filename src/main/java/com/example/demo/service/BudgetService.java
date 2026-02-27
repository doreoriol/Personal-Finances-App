package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.BudgetRequest;
import com.example.demo.model.Budget;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public BudgetService(BudgetRepository budgetRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public Budget findById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));
    }

    public Budget create(BudgetRequest budgetRequest) {
        Category category = findCategoryById(budgetRequest.getCategoryId());
        User user = findUserById(budgetRequest.getUserId());

        Budget budget = new Budget();
        budget.setMonth(budgetRequest.getMonth());
        budget.setLimitAmount(budgetRequest.getLimitAmount());
        budget.setSpentAmount(budgetRequest.getSpentAmount());
        budget.setCategory(category);
        budget.setUser(user);
        return budgetRepository.save(budget);
    }

    public Budget update(Long id, BudgetRequest budgetRequest) {
        Category category = findCategoryById(budgetRequest.getCategoryId());
        User user = findUserById(budgetRequest.getUserId());

        Budget existing = findById(id);
        existing.setMonth(budgetRequest.getMonth());
        existing.setLimitAmount(budgetRequest.getLimitAmount());
        existing.setSpentAmount(budgetRequest.getSpentAmount());
        existing.setCategory(category);
        existing.setUser(user);
        return budgetRepository.save(existing);
    }

    public void delete(Long id) {
        Budget existing = findById(id);
        budgetRepository.delete(existing);
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
