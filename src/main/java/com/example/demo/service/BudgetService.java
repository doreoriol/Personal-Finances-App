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

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUserService currentUserService;

    public BudgetService(BudgetRepository budgetRepository,
                         CategoryRepository categoryRepository,
                         CurrentUserService currentUserService) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.currentUserService = currentUserService;
    }

    public List<Budget> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return budgetRepository.findByUserId(userId);
    }

    public Budget findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return budgetRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));
    }

    public Budget create(BudgetRequest budgetRequest) {
        Category category = findCategoryById(budgetRequest.getCategoryId());
        User user = currentUserService.getCurrentUser();

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
        User user = currentUserService.getCurrentUser();

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

}
