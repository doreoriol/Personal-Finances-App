package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final CurrentUserService currentUserService;
    private final ResponseMapper responseMapper;

    public AccountService(AccountRepository accountRepository,
                          CurrentUserService currentUserService,
                          ResponseMapper responseMapper) {
        this.accountRepository = accountRepository;
        this.currentUserService = currentUserService;
        this.responseMapper = responseMapper;
    }

    public List<AccountResponse> findAll() {
        long userId = currentUserService.getCurrentUser().getId();
        return accountRepository.findByUserId(userId).stream()
                .map(responseMapper::toAccountResponse)
                .toList();
    }

    public AccountResponse findById(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        return responseMapper.toAccountResponse(findEntityByIdAndUserId(id, userId));
    }

    public AccountResponse create(AccountRequest request) {
        Account account = new Account();
        var user = currentUserService.getCurrentUser();

        account.setName(request.getName());
        account.setType(request.getType());
        account.setBalance(request.getBalance());
        account.setCurrency(request.getCurrency());
        account.setUser(user);
        return responseMapper.toAccountResponse(accountRepository.save(account));
    }

    public AccountResponse update(Long id, AccountRequest request) {
        long userId = currentUserService.getCurrentUser().getId();
        Account existing = findEntityByIdAndUserId(id, userId);
        var user = currentUserService.getCurrentUser();

        existing.setName(request.getName());
        existing.setType(request.getType());
        existing.setBalance(request.getBalance());
        existing.setCurrency(request.getCurrency());
        existing.setUser(user);
        return responseMapper.toAccountResponse(accountRepository.save(existing));
    }

    public void delete(Long id) {
        long userId = currentUserService.getCurrentUser().getId();
        Account existing = findEntityByIdAndUserId(id, userId);
        accountRepository.delete(existing);
    }

    private Account findEntityByIdAndUserId(Long id, long userId) {
        return accountRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }
}
