package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Budget;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long>{

    Page<Budget> findByUserId(long userId, Pageable pageable);

    java.util.Optional<Budget> findByIdAndUserId(Long id, long userId);
}
