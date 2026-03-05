package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.model.Category;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CurrentUserService currentUserService;

    public CategoryService(CategoryRepository categoryRepository, CurrentUserService currentUserService) {
        this.categoryRepository = categoryRepository;
        this.currentUserService = currentUserService;
    }

    public List<Category> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return categoryRepository.findByUserId(userId);
    }

    public Category findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return categoryRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    public Category create (Category category) {
        var user = currentUserService.getCurrentUser();
        category.setUser(user);
        return categoryRepository.save(category);
    }

    public Category update (Long id, Category category) {
        Category existing = findById(id);
        existing.setName(category.getName());
        existing.setType(category.getType());
        existing.setUser(currentUserService.getCurrentUser());
        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        Category existing = findById(id);
        categoryRepository.delete(existing);
    }

}
