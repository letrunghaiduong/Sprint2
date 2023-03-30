package com.example.seafoodbe.model;

public interface ICart {
    public Integer getId();
    public Integer getProductId();
    public Integer getQuantity();
    public String getImage();
    public String getName();
    public double getPrice();
    public double getSize();
}
