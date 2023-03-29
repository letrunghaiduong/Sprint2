package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.OrderDetail;
import com.example.seafoodbe.repository.IOrderDetailRepository;
import com.example.seafoodbe.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository orderDetailRepository;


    @Override
    public void addNew(Integer productId, Integer userId,double size) {
        orderDetailRepository.addNew(productId, userId,size);
    }

    @Override
    public List<ICart> getAll(Integer userId) {
        return orderDetailRepository.getAll(userId);
    }

    @Override
    public void update(double size, Integer productId) {
        orderDetailRepository.update(size, productId);
    }

    @Override
    public OrderDetail findByProductId(Integer productId) {
        return orderDetailRepository.findByProductId(productId);
    }

}
