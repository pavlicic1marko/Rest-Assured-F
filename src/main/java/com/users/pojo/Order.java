package com.users.pojo;

import java.util.List;

public class Order {

    private ShippingAddress shippingAddress ;
    private List<Product>  orderItems;
    private String paymentMethod= "PayPal";
    private String itemsPrice= "29.99";
    private String shippingPrice= "10.00";
    private String taxPrice= "2.46";
    private String totalPrice= "42.45";

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
