package com.example.seafoodbe.model;

public interface IOrderProduct {
    public Integer getId();
    public double getTotalPrice();
    public String getOrderDate();
    public String getShippingAddress();
    public String getCode();
}
