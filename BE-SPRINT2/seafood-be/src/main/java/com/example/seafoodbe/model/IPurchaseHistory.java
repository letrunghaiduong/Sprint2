package com.example.seafoodbe.model;

public interface IPurchaseHistory {
    public Integer getId();
    public String getName();
    public String getImage();
    public double getTotalPrice();
    public Integer getQuantity();
    public String getOrderDate();
    public double getSize();
    public String getShippingAddress();


}
