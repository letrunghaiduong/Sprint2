package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.Image;
import com.example.seafoodbe.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISizeRepository extends JpaRepository<Size, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into size (size, quantity, product_id) value(:size,:quantity,:productId)", nativeQuery = true)
    void addSize(@Param("size") String size,@Param("quantity") Integer quantity, @Param("productId") Integer productId);



    @Query(value = "select * from size where product_id = :productId", nativeQuery = true)
    List<Size> findByIdProduct(@Param("productId") Integer productId);


    @Modifying
    @Transactional
    @Query(value = "update size set size.quantity = size.quantity - :quantity\n" +
            "            where size.product_id = :productId\n" +
            "                  and size.size = :size", nativeQuery = true)
    void update(@Param("quantity") Integer quantity,
                @Param("productId") Integer productId,
                @Param("size") double size);

    @Query(value = "select * from size where product_id = :productId and size.size = :size", nativeQuery = true)
    Size findQuantity(@Param("productId") Integer productId,
                            @Param("size") double size);
}
