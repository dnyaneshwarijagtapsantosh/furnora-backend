package com.furnora.furnora_backend.repository;

import com.furnora.furnora_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}