package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.AccountRequest;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;

    public AccountService(AccountRepository accountRepository, CurrentUserService currentUserService) {
        this.accountRepository = accountRepository;
        this.currentUserService = currentUserService;
    }

    public List<Account> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return accountRepository.findByUserId(userId);
    }

    public Account findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return accountRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    public Account create(AccountRequest request) {
        Account account = new Account();
        var user = currentUserService.getCurrentUser();

        account.setName(request.getName());
        account.setType(request.getType());
        account.setBalance(request.getBalance());
        account.setCurrency(request.getCurrency());
        account.setUser(user);
        return accountRepository.save(account);
    }

    public Account update(Long id, AccountRequest request) {
        Account existing = findById(id);
        var user = currentUserService.getCurrentUser();

        existing.setName(request.getName());
        existing.setType(request.getType());
        existing.setBalance(request.getBalance());
        existing.setCurrency(request.getCurrency());
        existing.setUser(user);
        return accountRepository.save(existing);
    }

    public void delete(Long id) {
        Account existing = findById(id);
        accountRepository.delete(existing);
    }
}
