package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TransactionPageResponse;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.dto.TransactionResponse;
import com.example.demo.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponse> getAll() {
        return transactionService.findAll();
    }

    @GetMapping("/search")
    public TransactionPageResponse search(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return transactionService.findAllPaged(from, to, categoryId, page, size);
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(@Valid @RequestBody TransactionRequest transaction) {
        return transactionService.create(transaction);
    }

    @PutMapping("/{id}")
    public TransactionResponse update(@PathVariable Long id, @Valid @RequestBody TransactionRequest transaction) {
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
