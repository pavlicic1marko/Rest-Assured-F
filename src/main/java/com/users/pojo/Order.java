package com.users.pojo;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Product>  orderItems =new ArrayList<Product>();


    public void setOrderItems(List<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private int countInStock;
    private int qty;
    private int product;
    private String paymentMethod;// "PayPal";
    private String itemsPrice;
    private String shippingPrice;
    private String taxPrice;
    private String totalPrice;

    public String getItemsPrice() {
        return itemsPrice;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setItemsPrice(String itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public String getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(String shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }



    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    private ShippingAddress shippingAddress ;

    public List<Product> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Product orderItems) {
        this.orderItems.add(orderItems);
    }

}
