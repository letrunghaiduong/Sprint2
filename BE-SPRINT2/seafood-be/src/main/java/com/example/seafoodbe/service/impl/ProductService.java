package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.IProduct;
import com.example.seafoodbe.model.ISellingProducts;
import com.example.seafoodbe.model.Product;
import com.example.seafoodbe.repository.IProductRepository;
import com.example.seafoodbe.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;


    @Override
    public void addProduct(String name, double price, Integer categoryId, Integer originId) {
        productRepository.addProduct(name, price, categoryId, originId);
    }

    @Override
    public Page<IProduct> showList(String search, Pageable pageable) {
        return productRepository.showList(search, pageable);
    }


    @Override
    public IProduct findByIdProduct(Integer productId) {
        return productRepository.findByIdProduct(productId);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ISellingProducts> sellingProducts() {
        return productRepository.sellingProducts();
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
