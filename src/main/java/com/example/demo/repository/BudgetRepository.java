package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Budget;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long>{

    List<Budget> findByUserId(long userId);

    java.util.Optional<Budget> findByIdAndUserId(Long id, long userId);
}
