package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(long userId);

    java.util.Optional<Account> findByIdAndUserId(Long id, long userId);
}
