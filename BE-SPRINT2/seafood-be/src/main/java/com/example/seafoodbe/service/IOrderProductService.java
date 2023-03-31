package com.example.seafoodbe.service;

import org.springframework.data.repository.query.Param;

public interface IOrderProductService {
    void addOrder(String oderDate,String shippingAddress,double totalPrice,Integer orderDetailId);
}
