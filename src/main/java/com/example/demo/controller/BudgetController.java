package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BudgetRequest;
import com.example.demo.model.Budget;
import com.example.demo.service.BudgetService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/budgets")
@Validated
public class BudgetController {

    private final BudgetService budgetService;
    
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public List<Budget> getAll() {
        return budgetService.findAll();
    }

    @GetMapping("/{id}")
    public Budget getById(@PathVariable Long id) {
        return budgetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Budget create(@Valid @RequestBody BudgetRequest entity) {
        return budgetService.create(entity);
    }

    @PutMapping("/{id}")
    public Budget update(@PathVariable Long id, @Valid @RequestBody BudgetRequest entity) {
        return budgetService.update(id, entity);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        budgetService.delete(id);
    }
    
}
