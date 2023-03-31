package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.repository.IOrderProductRepository;
import com.example.seafoodbe.service.IOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService implements IOrderProductService {

    @Autowired
    IOrderProductRepository orderProductRepository;

    @Override
    public void addOrder(String oderDate, String shippingAddress, double totalPrice, Integer orderDetailId) {
        orderProductRepository.addOrder(oderDate, shippingAddress, totalPrice, orderDetailId);
    }
}
