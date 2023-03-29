package com.example.seafoodbe.service;

import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.OrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailService {
    void addNew(Integer productId, Integer userId,double size);

    List<ICart> getAll(Integer userId);

    void update(double size, Integer productId);

    OrderDetail findByProductId(Integer productId);
}
