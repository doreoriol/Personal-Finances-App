package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.BudgetResponse;
import com.example.demo.dto.BudgetRequest;
import com.example.demo.model.Budget;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUserService currentUserService;
    private final ResponseMapper responseMapper;

    public List<BudgetResponse> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return budgetRepository.findByUserId(userId).stream()
                .map(responseMapper::toBudgetResponse)
                .toList();
    }

    public BudgetResponse findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return responseMapper.toBudgetResponse(findEntityByIdAndUserId(id, userId));
    }

    public BudgetResponse create(BudgetRequest budgetRequest) {
        Category category = findCategoryById(budgetRequest.getCategoryId());
        User user = currentUserService.getCurrentUser();

        Budget budget = new Budget();
        budget.setMonth(budgetRequest.getMonth());
        budget.setLimitAmount(budgetRequest.getLimitAmount());
        budget.setSpentAmount(budgetRequest.getSpentAmount());
        budget.setCategory(category);
        budget.setUser(user);
        return responseMapper.toBudgetResponse(budgetRepository.save(budget));
    }

    public BudgetResponse update(Long id, BudgetRequest budgetRequest) {
        Category category = findCategoryById(budgetRequest.getCategoryId());
        User user = currentUserService.getCurrentUser();

        Budget existing = findEntityByIdAndUserId(id, user.getId());
        existing.setMonth(budgetRequest.getMonth());
        existing.setLimitAmount(budgetRequest.getLimitAmount());
        existing.setSpentAmount(budgetRequest.getSpentAmount());
        existing.setCategory(category);
        existing.setUser(user);
        return responseMapper.toBudgetResponse(budgetRepository.save(existing));
    }

    public void delete(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        Budget existing = findEntityByIdAndUserId(id, userId);
        budgetRepository.delete(existing);
    }

    private Category findCategoryById(Long categoryId) {
        long userId = currentUserService.getCurrentUser().getId();
        return categoryRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    private Budget findEntityByIdAndUserId(Long id, long userId) {
        return budgetRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));
    }

}
