package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.Category;
import com.example.seafoodbe.repository.ICategoryRepository;
import com.example.seafoodbe.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
