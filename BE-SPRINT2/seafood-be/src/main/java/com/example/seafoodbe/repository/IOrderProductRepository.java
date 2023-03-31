package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into order_product (oder_date, shipping_address, total_price, order_detail_id)\n" +
            "values (:oderDate, :shippingAddress, :totalPrice, :orderDetailId)", nativeQuery = true)
    void addOrder(@Param("oderDate") String oderDate,
                  @Param("shippingAddress") String shippingAddress,
                  @Param("totalPrice") double totalPrice,
                  @Param("orderDetailId") Integer orderDetailId);

}
