package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.IProduct;
import com.example.seafoodbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select product.id as id,image as image,name as name,\n" +
            "       price as price,c.category_name as category,o.origin_name as origin\n" +
            "from product\n" +
            "    join category c on c.id = product.category_id\n" +
            "    join origin o on o.id = product.origin_id\n" +
            "                        where (name like concat('%', :search , '%')\n" +
            "                               and  flag_delete = false)", nativeQuery = true,
    countQuery = "select product.id as id,image as image,name as name,\n" +
            "       price as price,c.category_name as category,o.origin_name as origin\n" +
            "from product\n" +
            "    join category c on c.id = product.category_id\n" +
            "    join origin o on o.id = product.origin_id\n" +
            "                        where (name like concat('%', :search , '%')\n" +
            "                               and  flag_delete = false)")
    Page<IProduct> showList(@Param("search") String search, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "insert into product(name, price, category_id, origin_id)\n" +
            "value(:name,:price,:categoryId,:originId)",
            nativeQuery = true)
    void addProduct(@Param("name") String name, @Param("price") double price,
                    @Param("categoryId") Integer categoryId, @Param("originId") Integer originId);

}
