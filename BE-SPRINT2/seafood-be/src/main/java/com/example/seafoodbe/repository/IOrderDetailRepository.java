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
    @Query(value = "insert into order_detail (product_id, user_id, size) values (:productId, :userId, :size)", nativeQuery = true)
    void addNew(@Param("productId") Integer productId,
                 @Param("userId") Integer userId,
                 @Param("size") double size);


    @Query(value = "select p.name as name, p.image as image, p.price as price, od.id as id, od.size as size\n" +
            "from order_detail as od\n" +
            "         join user u on u.id = od.user_id\n" +
            "         join product p on p.id = od.product_id\n" +
            "where od.flag_delete = false and user_id = :userId",
            nativeQuery = true)
    List<ICart> getAll(@Param("userId") Integer userId);


    @Modifying
    @Transactional
    @Query(value = "update order_detail set size = size + :size where product_id = :productId", nativeQuery = true)
    void update(@Param("size") double size, @Param("productId") Integer productId);


    @Query(value = "select * from order_detail where product_id = :productId", nativeQuery = true)
    OrderDetail findByProductId(@Param("productId") Integer productId);
}
