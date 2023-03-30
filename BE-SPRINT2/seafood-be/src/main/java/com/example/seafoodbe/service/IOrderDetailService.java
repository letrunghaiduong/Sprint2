package com.example.seafoodbe.service;

import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.OrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailService {
    void addNew(Integer productId, Integer userId,Integer quantity,double size);

    List<ICart> getAll(Integer userId);

    void update(Integer quantity, Integer productId,double size);

    void updateQuantity(Integer quantity, Integer productId,double size);

    OrderDetail[] findByProductId(Integer productId);

    void delete(Integer id);
}
