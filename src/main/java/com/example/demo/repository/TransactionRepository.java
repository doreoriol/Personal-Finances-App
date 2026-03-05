package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Transaction;

import java.util.List;
import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(long userId);

    java.util.Optional<Transaction> findByIdAndUserId(Long id, long userId);

    List<Transaction> findByUserIdAndDateBetween(long userId, LocalDate from, LocalDate to);

    List<Transaction> findTop5ByUserIdOrderByDateDesc(long userId);
}
