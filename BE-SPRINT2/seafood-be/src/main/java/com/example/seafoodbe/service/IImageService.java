package com.example.seafoodbe.service;

import org.springframework.data.repository.query.Param;

public interface IImageService {
    void addImage(String image, Integer productId);

}
