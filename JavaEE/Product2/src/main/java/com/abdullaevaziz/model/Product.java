package com.abdullaevaziz.model;

public class Product {
    private String name;
    private String description;
    private double price;
    private int quantityOnHand;
    private int minOrderQuantity;

    public Product() {
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.quantityOnHand = 0;
        this.minOrderQuantity = 0;
    }

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityOnHand = new RandomNumberGenerator(0, 10).generate();
        this.minOrderQuantity = new RandomNumberGenerator(1, 5).generate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(int minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public void display() {
        System.out.println("Product: " + name + ", Description: " + description +
                ", Price: $" + price + ", Stock: " + quantityOnHand +
                ", Min Order: " + minOrderQuantity);
    }
}