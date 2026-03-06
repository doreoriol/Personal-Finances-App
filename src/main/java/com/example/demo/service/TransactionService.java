package com.example.demo.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.TransactionResponse;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionPageResponse;
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
    private final ResponseMapper responseMapper;

    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository,
                              CurrentUserService currentUserService,
                              ResponseMapper responseMapper) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.currentUserService = currentUserService;
        this.responseMapper = responseMapper;
    }

    public List<TransactionResponse> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return transactionRepository.findByUserId(userId).stream()
                .map(responseMapper::toTransactionResponse)
                .toList();
    }

    public TransactionPageResponse findAllPaged(LocalDate from, LocalDate to, Long categoryId, int page, int size) {
        var user = currentUserService.getCurrentUser();

        LocalDate start = from != null ? from : LocalDate.now().withDayOfMonth(1);
        LocalDate end = to != null ? to : LocalDate.now();

        List<Transaction> all = transactionRepository.findByUserIdAndDateBetween(user.getId(), start, end);

        if (categoryId != null) {
            all = all.stream()
                    .filter(t -> Objects.equals(t.getCategory().getId(), categoryId))
                    .toList();
        }

        all = all.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed()
                        .thenComparing(Transaction::getId).reversed())
                .toList();

        int totalElements = all.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        List<TransactionResponse> pageContent = List.of();
        if (fromIndex < totalElements) {
            pageContent = all.subList(fromIndex, toIndex).stream()
                    .map(responseMapper::toTransactionResponse)
                    .toList();
        }

        TransactionPageResponse response = new TransactionPageResponse();
        response.setContent(pageContent);
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);

        return response;
    }

    public TransactionResponse findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return responseMapper.toTransactionResponse(findEntityByIdAndUserId(id, userId));
    }

    public TransactionResponse create(TransactionRequest request) {
        Transaction transaction = new Transaction();
        Category category = findCategoryById(request.getCategoryId());
        User user = currentUserService.getCurrentUser();

        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setCategory(category);
        transaction.setUser(user);
        transaction.setPaymentMethod(request.getPaymentMethod());
        return responseMapper.toTransactionResponse(transactionRepository.save(transaction));
    }

    public TransactionResponse update(Long id, TransactionRequest request) {
        long userId = currentUserService.getCurrentUser().getId();
        Transaction transaction = findEntityByIdAndUserId(id, userId);
        Category category = findCategoryById(request.getCategoryId());
        User user = currentUserService.getCurrentUser();

        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setCategory(category);
        transaction.setUser(user);
        transaction.setPaymentMethod(request.getPaymentMethod());
        return responseMapper.toTransactionResponse(transactionRepository.save(transaction));
    }

    public void delete(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        Transaction existing = findEntityByIdAndUserId(id, userId);
        transactionRepository.delete(existing);
    }

    public Category findCategoryById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return categoryRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    private Transaction findEntityByIdAndUserId(Long id, long userId) {
        return transactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

}
