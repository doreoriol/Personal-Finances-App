package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Transaction;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByUserId(long userId, Pageable pageable);

    java.util.Optional<Transaction> findByIdAndUserId(Long id, long userId);

    List<Transaction> findByUserIdAndDateBetween(long userId, LocalDate from, LocalDate to);

    List<Transaction> findTop5ByUserIdOrderByDateDesc(long userId);
}
