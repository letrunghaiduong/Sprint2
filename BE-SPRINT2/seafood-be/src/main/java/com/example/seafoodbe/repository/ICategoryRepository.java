package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
