package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.ICart;
import com.example.seafoodbe.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into order_detail (product_id, user_id, quantity, size) values (:productId, :userId, :quantity, :size)", nativeQuery = true)
    void addNew(@Param("productId") Integer productId,
                 @Param("userId") Integer userId,
                 @Param("quantity") Integer quantity,
                 @Param("size") double size);


    @Query(value = "select p.id as productId, p.name as name, p.image as image, p.price as price,\n" +
            "       od.id as id, od.size as size, od.quantity as quantity\n" +
            "            from order_detail as od\n" +
            "                     join user u on u.id = od.user_id\n" +
            "                     join product p on p.id = od.product_id\n" +
            "            where od.flag_delete = false and user_id = :userId",
            nativeQuery = true)
    List<ICart> getAll(@Param("userId") Integer userId);


    @Modifying
    @Transactional
    @Query(value = "update order_detail set quantity = quantity + :quantity\n" +
            "where product_id = :productId and size =:size and user_id = :userId", nativeQuery = true)
    void update(@Param("quantity") Integer quantity,
                @Param("productId") Integer productId,
                @Param("size") double size,
                @Param("userId") Integer userId);



    @Modifying
    @Transactional
    @Query(value = "update order_detail set quantity = :quantity\n" +
            "            where product_id = :productId and size =:size and user_id = :userId", nativeQuery = true)
    void updateQuantity(@Param("quantity") Integer quantity,
                        @Param("productId") Integer productId,
                        @Param("size") double size,
                        @Param("userId") Integer userId);





    @Query(value = "select * from order_detail where product_id = :productId and user_id = :userId", nativeQuery = true)
    OrderDetail[] findByProductId(@Param("productId") Integer productId,
                                  @Param("userId") Integer userId);


    @Modifying
    @Transactional
    @Query(value = "update order_detail set order_detail.flag_delete = true\n" +
            "            where order_detail.user_id = :userId", nativeQuery = true)
    void setFlagDelete(@Param("userId") Integer userId);


    @Query(value = "select * from order_detail where product_id = :productId and user_id = :userId " +
            "and size = :size and flag_delete = false", nativeQuery = true)
    OrderDetail findOrderDetail(@Param("productId") Integer productId,
                                  @Param("userId") Integer userId,
                                  @Param("size") double size);
}
