package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.repository.ISizeRepository;
import com.example.seafoodbe.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;

public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;


    @Override
    public void addSize(String size, Integer quantity, Integer productId) {
        sizeRepository.addSize(size, quantity, productId);
    }
}
