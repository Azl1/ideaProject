package com.kirillkotov.model;

import java.util.Objects;

public class TV {
    private String brand;
    private String model;
    private String color;
    private int timeExpectancy;
    private double price;

    private Size size = new Size();

    public TV() {
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTimeExpectancy() {
        return timeExpectancy;
    }

    public void setTimeExpectancy(int timeExpectancy) {
        this.timeExpectancy = timeExpectancy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
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

    public String toCSV() {
        return this.brand + ";" + this.model + ";"
                + this.color + ";" + this.timeExpectancy + ";" + this.price + ";"
                + this.size.toCSV();
    }
}
