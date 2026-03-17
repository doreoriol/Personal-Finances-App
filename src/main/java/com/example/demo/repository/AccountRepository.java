package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Page<Account> findByUserId(long userId, Pageable pageable);

    java.util.Optional<Account> findByIdAndUserId(Long id, long userId);
}
