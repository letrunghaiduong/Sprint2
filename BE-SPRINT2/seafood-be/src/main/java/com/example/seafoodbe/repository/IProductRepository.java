package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select  * from   product\n" +
            "where (name like concat('%', :search , '%')\n" +
            "           and  flag_delete = false)", nativeQuery = true,
    countQuery = "select  * from   product\n" +
            "where (name like concat('%', :search , '%')\n" +
            "           and  flag_delete = false)")
    Page<Product> showList(@Param("search") String search, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "insert into product(name, price, category_id, origin_id)\n" +
            "value(:name,:price,:categoryId,:originId)",
            nativeQuery = true)
    void addProduct(@Param("name") String name, @Param("price") double price,
                    @Param("categoryId") Integer categoryId, @Param("originId") Integer originId);

}
