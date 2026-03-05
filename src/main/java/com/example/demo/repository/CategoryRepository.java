package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    List<Category> findByUserId(long userId);

    java.util.Optional<Category> findByIdAndUserId(Long id, long userId);
}
