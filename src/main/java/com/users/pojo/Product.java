package com.users.pojo;

public class Product {
    private String user;
    private String name;
    private String price;
    private String brand;
    private int countInStock;
    private String category;
    private String description;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private int qty;


    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    private int product;

    // Default constructor
    public Product() {
    }

    // Constructor with parameters
    public Product(String user, String name, String price, String brand, int countInStock, String category, String description) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.countInStock = countInStock;
        this.category = category;
        this.description = description;

    }

    // Getters and Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method for easy printing

}
