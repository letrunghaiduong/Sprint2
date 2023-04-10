package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.IOrderProduct;
import com.example.seafoodbe.model.IPurchaseHistory;
import com.example.seafoodbe.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into order_product (oder_date, shipping_address, total_price, order_detail_id,code)\n" +
            "values (:oderDate, :shippingAddress, :totalPrice, :orderDetailId, :code)", nativeQuery = true)
    void addOrder(@Param("oderDate") String oderDate,
                  @Param("shippingAddress") String shippingAddress,
                  @Param("totalPrice") double totalPrice,
                  @Param("orderDetailId") Integer orderDetailId,
                  @Param("code") String code);


    @Query(value = "select sum(total_price)               as totalPrice,\n" +
            "       order_product.oder_date        as orderDate,\n" +
            "       order_product.id               as id,\n" +
            "       order_product.shipping_address as shippingAddress,\n" +
            "       order_product.code             as code\n" +
            "from order_product\n" +
            "         join order_detail od on od.id = order_product.order_detail_id\n" +
            "         join user u on u.id = od.user_id\n" +
            "where user_id = :userId\n" +
            "group by order_product.code\n" +
            "order by order_product.id desc", nativeQuery = true)
    List<IOrderProduct> getAllOrder(@Param("userId") Integer userId);


    @Query(value = "select p.name as name, p.image as image, op.total_price as totalPrice,\n" +
            "       op.id as id, od.size as size, od.quantity as quantity,\n" +
            "       op.oder_date as orderDate, op.shipping_address as shippingAddress\n" +
            "from order_product as op\n" +
            "             join order_detail od on od.id = op.order_detail_id\n" +
            "             join user u on u.id = od.user_id\n" +
            "             join product p on p.id = od.product_id\n" +
            "            where op.code = :code", nativeQuery = true)
    List<IPurchaseHistory> detailPurchase(@Param("code") String code);



}
