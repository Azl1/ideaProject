package com.kirillkotov.model;

public class TV {
    private String brand;
    private String model;
    private String color;
    private int timeExpectancy;
    private double price;

    public TV() {
    }

    public TV(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
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
        return this.timeExpectancy;
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

    /**
     *
     * @param value
     * @return
     */
    public double evalPrice(double value){
        this.price += value;
        return this.price;
    }

    public double evalPrice(int value){
        this.price += value;
        return this.price;
    }

    public double evalPrice(String value){
        this.price += Double.parseDouble(value);
        return this.price;
    }

    @Override
    public String toString() {
        return "TV{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", timeExpectancy=" + timeExpectancy +
                ", price=" + price +
                '}';
    }
}
