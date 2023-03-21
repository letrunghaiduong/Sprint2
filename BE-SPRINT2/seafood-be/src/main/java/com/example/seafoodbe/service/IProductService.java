package com.example.seafoodbe.service;

import com.example.seafoodbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IProductService {

    void addProduct(String name, double price, Integer categoryId, Integer originId);
    Page<Product> showList(String search, Pageable pageable);
}
