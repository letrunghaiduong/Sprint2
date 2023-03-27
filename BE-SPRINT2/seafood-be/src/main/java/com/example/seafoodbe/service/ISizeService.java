package com.example.seafoodbe.service;

import com.example.seafoodbe.model.Size;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISizeService {
    void addSize(String size, Integer quantity, Integer productId);
    List<Size> findByIdProduct(Integer productId);
}
