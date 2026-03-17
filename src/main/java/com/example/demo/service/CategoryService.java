package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.model.Category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CurrentUserService currentUserService;
    private final ResponseMapper responseMapper;

    public Page<CategoryResponse> findAll(Pageable pageable) {
        long userId = currentUserService.getCurrentUser().getId();
        Page<Category> categories = categoryRepository.findByUserId(userId, pageable);
        return categories.map(responseMapper::toCategoryResponse);
    }

    public CategoryResponse findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return responseMapper.toCategoryResponse(findEntityByIdAndUserId(id, userId));
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = new Category();
        var user = currentUserService.getCurrentUser();
        category.setName(categoryRequest.getName());
        category.setType(categoryRequest.getType());
        category.setUser(user);
        return responseMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        long userId = currentUserService.getCurrentUser().getId();
        Category existing = findEntityByIdAndUserId(id, userId);
        existing.setName(categoryRequest.getName());
        existing.setType(categoryRequest.getType());
        existing.setUser(currentUserService.getCurrentUser());
        return responseMapper.toCategoryResponse(categoryRepository.save(existing));
    }

    public void delete(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        Category existing = findEntityByIdAndUserId(id, userId);
        categoryRepository.delete(existing);
    }

    private Category findEntityByIdAndUserId(Long id, long userId) {
        return categoryRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

}
