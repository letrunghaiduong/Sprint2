package com.example.seafoodbe.repository;

import com.example.seafoodbe.model.IProduct;
import com.example.seafoodbe.model.Image;
import com.example.seafoodbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select product.id as id,image as image,name as name,price as price,c.category_name as category,o.origin_name as origin\n" +
            "                        from product\n" +
            "                            join category c on c.id = product.category_id\n" +
            "                            join origin o on o.id = product.origin_id\n" +
            "                                                where product.flag_delete = false and\n" +
            "                                                      (product.name like concat('%', :search , '%')\n" +
            "                                                           or c.category_name like concat('%', :search , '%'))\n" +
            "    order by product.import_date\n", nativeQuery = true,
    countQuery = "select product.id as id,image as image,name as name,price as price,c.category_name as category,o.origin_name as origin\n" +
            "                        from product\n" +
            "                            join category c on c.id = product.category_id\n" +
            "                            join origin o on o.id = product.origin_id\n" +
            "                                                where product.flag_delete = false and\n" +
            "                                                      (product.name like concat('%', :search , '%')\n" +
            "                                                           or c.category_name like concat('%', :search , '%'))\n" +
            "    order by product.import_date")
    Page<IProduct> showList(@Param("search") String search, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "insert into product(name, price, category_id, origin_id)\n" +
            "value(:name,:price,:categoryId,:originId)",
            nativeQuery = true)
    void addProduct(@Param("name") String name, @Param("price") double price,
                    @Param("categoryId") Integer categoryId, @Param("originId") Integer originId);


    @Query(value = "select p.id as id, p.price as price,p.name as name,p.image as image,\n" +
            "       c.category_name as category,o.origin_name as origin\n" +
            "from product as p\n" +
            "join category c on c.id = p.category_id\n" +
            "join origin o on o.id = p.origin_id\n" +
            "where p.id = :productId",nativeQuery = true)
    IProduct findByIdProduct(@Param("productId") Integer productId);
}
