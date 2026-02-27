package com.example.demo.service;

import java.util.List;
import com.example.demo.repository.UserRepository;
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
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository,
            UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    public Transaction create(TransactionRequest request) {
        Transaction transaction = new Transaction();
        Category category = findCategoryById(request.getCategory_id());
        User user = findUserById(request.getUser_id());

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
        User user = findUserById(request.getUser_id());

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

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
