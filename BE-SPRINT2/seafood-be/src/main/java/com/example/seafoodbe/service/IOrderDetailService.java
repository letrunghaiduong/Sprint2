package com.example.seafoodbe.service;

import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.OrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    void addNew(Integer productId, Integer userId,Integer quantity,double size);

    List<ICart> getAll(Integer userId);

    void update(Integer quantity,Integer productId,double size,Integer userId);

    void updateQuantity(Integer quantity,Integer productId,double size,Integer userId);

    OrderDetail[] findByProductId(Integer productId,Integer userId);
    void delete(Integer id);

    void setFlagDelete(Integer userId);

    OrderDetail findOrderDetail(Integer productId,Integer userId,double size);


}
