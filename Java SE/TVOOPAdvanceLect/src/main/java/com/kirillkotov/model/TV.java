package com.kirillkotov.model;

import java.util.Objects;

/**
 * @author Kirill Kotov
 */
public class TV {
    public static final int TV_ID = 1;
    public static final String BRAND_COMMON = "Samsung";
    private String brand;
    private String model;
    private String color;
    private int timeExpectancy;
    private double price = 1;

    private Size size = new Size();

    private static int count = 0;

    {
        /*System.out.println("This is creating new object");*/
        count++;
    }

    static {

    }

    public TV() {
    }

    public TV(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price) {
        this(brand, model);
        this.color = color;
        this.timeExpectancy = timeExpectancy;
        this.price = price;
    }

    public TV(String brand, String model, String color, int timeExpectancy, double price, Size size) {
        this(brand, model, color, timeExpectancy, price);
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public static int getCount() {
        return count;
    }

    /**
     * Evaluate price
     * @param value
     * @return new price
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

    /**
     * Calculate avg price of TV array
     * @param mass TV array
     * @return avg price
     */
    public static double avgPrice(TV[] mass){
        double sum = 0;
        for (TV tv : mass) {
            sum += tv.price;
        }
        return sum / mass.length;
    }

}
