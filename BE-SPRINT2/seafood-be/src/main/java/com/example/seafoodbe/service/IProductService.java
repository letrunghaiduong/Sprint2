package com.example.seafoodbe.service;

import com.example.seafoodbe.model.IProduct;
import com.example.seafoodbe.model.ISellingProducts;
import com.example.seafoodbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    void addProduct(String name, double price, Integer categoryId, Integer originId);
    Page<IProduct> showList(String search, Pageable pageable);

    IProduct findByIdProduct(Integer productId);
    Optional<Product> findById(Integer id);

    List<ISellingProducts> sellingProducts();

    Page<Product> getAll(Pageable pageable);
}
