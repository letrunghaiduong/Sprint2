package com.example.seafoodbe.service;

import com.example.seafoodbe.model.IOrderProduct;
import com.example.seafoodbe.model.IPurchaseHistory;
import com.example.seafoodbe.model.OrderProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderProductService {
    void addOrder(String oderDate,String shippingAddress,double totalPrice,Integer orderDetailId,String maDonHang);

    List<IPurchaseHistory> detailPurchase(String code);

    List<IOrderProduct> getAllOrder(Integer userId);

}
