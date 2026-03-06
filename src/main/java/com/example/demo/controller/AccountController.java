package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {
        
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@Valid @RequestBody AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    @PutMapping("/{id}")
    public AccountResponse update(@Valid @RequestBody AccountRequest accountRequest, @PathVariable Long id) {
        return accountService.update(id, accountRequest);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
    
}
