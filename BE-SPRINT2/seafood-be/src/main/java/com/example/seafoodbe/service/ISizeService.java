package com.example.seafoodbe.service;

import org.springframework.data.repository.query.Param;

public interface ISizeService {
    void addSize(String size, Integer quantity, Integer productId);

}
