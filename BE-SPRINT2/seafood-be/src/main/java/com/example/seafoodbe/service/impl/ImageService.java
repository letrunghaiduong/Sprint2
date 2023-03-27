package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.Image;
import com.example.seafoodbe.repository.IImageRepository;
import com.example.seafoodbe.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;



    @Override
    public List<Image> findByIdProduct(Integer productId) {
        return imageRepository.findByIdProduct(productId);
    }
}
