package com.example.seafoodbe.service;

import com.example.seafoodbe.model.Image;

import java.util.List;

public interface IImageService {
//    void addImage(String image, Integer productId);
    List<Image> findByIdProduct(Integer productId);
}
