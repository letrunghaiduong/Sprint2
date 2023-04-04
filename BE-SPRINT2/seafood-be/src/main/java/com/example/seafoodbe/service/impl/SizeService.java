package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.Size;
import com.example.seafoodbe.repository.ISizeRepository;
import com.example.seafoodbe.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizeService implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;


    @Override
    public void addSize(String size, Integer quantity, Integer productId) {
        sizeRepository.addSize(size, quantity, productId);
    }

    @Override
    public List<Size> findByIdProduct(Integer productId) {
        return sizeRepository.findByIdProduct(productId);
    }

    @Override
    public void update(Integer quantity, Integer productId, double size) {
        sizeRepository.update(quantity, productId, size);
    }

    @Override
    public Size findQuantity(Integer productId, double size) {
        return sizeRepository.findQuantity(productId, size);
    }
}
