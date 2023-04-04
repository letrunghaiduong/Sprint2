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
    public void addNew(Integer productId, Integer userId,Integer quantity,double size) {
        orderDetailRepository.addNew(productId, userId, quantity,size);
    }

    @Override
    public List<ICart> getAll(Integer userId) {
        return orderDetailRepository.getAll(userId);
    }

    @Override
    public void update(Integer quantity,Integer productId,double size,Integer userId) {
        orderDetailRepository.update(quantity, productId, size, userId);
    }

    @Override
    public void updateQuantity(Integer quantity, Integer productId, double size, Integer userId) {
        orderDetailRepository.updateQuantity(quantity, productId, size, userId);
    }

    @Override
    public OrderDetail[] findByProductId(Integer productId,Integer userId) {
        return orderDetailRepository.findByProductId(productId,userId);
    }

    @Override
    public void delete(Integer id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public void setFlagDelete(Integer userId) {
        orderDetailRepository.setFlagDelete(userId);
    }

    @Override
    public OrderDetail findOrderDetail(Integer productId, Integer userId, double size) {
        return orderDetailRepository.findOrderDetail(productId, userId, size);
    }

}
