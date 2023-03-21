package com.example.seafoodbe.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Origin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String originName;

    @OneToMany(mappedBy = "origin")
    private List<Product> productList;

    public Origin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return originName;
    }

    public void setOrigin(String origin) {
        this.originName = origin;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
