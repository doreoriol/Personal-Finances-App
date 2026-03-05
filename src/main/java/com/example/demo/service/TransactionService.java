package com.example.demo.service;

import java.util.List;
import com.example.demo.service.CurrentUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.TransactionRequest;
import com.example.demo.model.Category;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUserService currentUserService;

    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository,
                              CurrentUserService currentUserService) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.currentUserService = currentUserService;
    }

    public List<Transaction> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return transactionRepository.findByUserId(userId);
    }

    public Transaction findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return transactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    public Transaction create(TransactionRequest request) {
        Transaction transaction = new Transaction();
        Category category = findCategoryById(request.getCategory_id());
        User user = currentUserService.getCurrentUser();

        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setCategory(category);
        transaction.setUser(user);
        transaction.setPaymentMethod(request.getPaymentMethod());
        return transactionRepository.save(transaction);
    }

    public Transaction update(Long id, TransactionRequest request) {
        Transaction transaction = findById(id);
        Category category = findCategoryById(request.getCategory_id());
        User user = currentUserService.getCurrentUser();

        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setCategory(category);
        transaction.setUser(user);
        transaction.setPaymentMethod(request.getPaymentMethod());
        return transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        Transaction existing = findById(id);
        transactionRepository.delete(existing);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

}
