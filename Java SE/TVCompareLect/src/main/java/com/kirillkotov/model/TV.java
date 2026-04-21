package com.kirillkotov.model;

import java.util.Objects;

public class TV implements Comparable<TV>{
    private String brand;
    private String model;
    private String color;
    private int timeExpectancy;
    private double price;

    private Size size = new Size();


    public TV() {
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price, Size size) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getTimeExpectancy() {
        return timeExpectancy;
    }

    public double getPrice() {
        return price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTimeExpectancy(int timeExpectancy) {
        this.timeExpectancy = timeExpectancy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TV tv = (TV) o;
        return timeExpectancy == tv.timeExpectancy && Double.compare(tv.price, price) == 0
                && Objects.equals(brand, tv.brand) && Objects.equals(model, tv.model)
                && Objects.equals(color, tv.color) && Objects.equals(size, tv.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, timeExpectancy, price, size);
    }

    @Override
    public String toString() {
        return "TV{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", timeExpectancy=" + timeExpectancy +
                ", price=" + price +
                ", size=" + size +
                '}';
    }

    @Override
    public int compareTo(TV o) {
        if(this.size.compareTo(o.size) == 0){
            return Double.compare(this.price, o.price);
        }
        return this.size.compareTo(o.size);
    }


}

