package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.IOrderProduct;
import com.example.seafoodbe.model.IPurchaseHistory;
import com.example.seafoodbe.model.OrderProduct;
import com.example.seafoodbe.repository.IOrderProductRepository;
import com.example.seafoodbe.service.IOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService implements IOrderProductService {

    @Autowired
    IOrderProductRepository orderProductRepository;

    @Override
    public void addOrder(String oderDate, String shippingAddress, double totalPrice, Integer orderDetailId,String code) {
        orderProductRepository.addOrder(oderDate, shippingAddress, totalPrice, orderDetailId,code);
    }

    @Override
    public List<IPurchaseHistory> detailPurchase(String code) {
        return orderProductRepository.detailPurchase(code);
    }

    @Override
    public List<IOrderProduct> getAllOrder(Integer userId) {
        return orderProductRepository.getAllOrder(userId);
    }
}
