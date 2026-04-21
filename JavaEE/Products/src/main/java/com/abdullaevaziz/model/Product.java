package com.abdullaevaziz.model;

import java.util.Objects;


public class Product {
    private String name;
    private String desc;
    private double price;
    private int qtyOnHand;
    private int minOrderQty;


    public Product(){}
    public Product(String name, String desc, double price) {
        this.name = name.toLowerCase();
        this.desc = desc;
        this.price = price;
        this.qtyOnHand = new  RandomNumberGenerator().generate(0,10);
        this.minOrderQty = new RandomNumberGenerator().generate(1,5);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 3 || name.length() >= 25){
            throw new IllegalArgumentException("Название товара должен быть от 3 до 25 символов!");
        }
        this.name = name.toLowerCase();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        if (desc.length() < 1 || desc.length() >= 50){
            throw new IllegalArgumentException("Описание товара должен быть от 0 до 50 символов!");
        }
        this.desc = desc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0!");
        }
        this.price = price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getMinOrderQty() {
        return minOrderQty;
    }

    public void setMinOrderQty(int minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return qtyOnHand == product.qtyOnHand && minOrderQty == product.minOrderQty && Objects.equals(name, product.name) && Objects.equals(desc, product.desc) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, price, qtyOnHand, minOrderQty);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                ", minOrderQty=" + minOrderQty +
                '}';
    }

    public void display() {
        System.out.println("\n=== Информация о продукте ===");
        System.out.println("Название: " + name);
        System.out.println("Описание: " + desc);
        System.out.printf("Цена: $%.2f\n", price);
        System.out.println("Количество на складе: " + qtyOnHand);
        System.out.println("Минимальный заказ: " + minOrderQty);
    }
}
