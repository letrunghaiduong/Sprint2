package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Integer> {


    @Modifying
    @Transactional
    @Query(value = "insert into image(image, product_id) value(:image, :productId)", nativeQuery = true)
    void addImage(@Param("image") String image,@Param("productId") Integer productId);

    @Query(value = "select * from image where product_id = :productId",nativeQuery = true)
    List<Image> findByIdProduct(@Param("productId") Integer productId);


}
