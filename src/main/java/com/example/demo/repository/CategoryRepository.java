package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Page<Category> findByUserId(long userId, Pageable pageable);

    java.util.Optional<Category> findByIdAndUserId(Long id, long userId);
}
