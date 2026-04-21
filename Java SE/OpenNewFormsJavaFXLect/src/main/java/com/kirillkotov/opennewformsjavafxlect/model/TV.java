package com.kirillkotov.opennewformsjavafxlect.model;

import java.util.Objects;

public class TV {
    private String brand;
    private String model;
    private int timeExpectancy;

    public TV() {
    }

    public TV(String brand, String model, int timeExpectancy) {
        this.brand = brand;
        this.model = model;
        this.timeExpectancy = timeExpectancy;
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

    public int getTimeExpectancy() {
        return timeExpectancy;
    }

    public void setTimeExpectancy(int timeExpectancy) {
        this.timeExpectancy = timeExpectancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TV tv = (TV) o;
        return timeExpectancy == tv.timeExpectancy && Objects.equals(brand, tv.brand) && Objects.equals(model, tv.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, timeExpectancy);
    }

    @Override
    public String toString() {
        return "TV{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", timeExpectancy=" + timeExpectancy +
                '}';
    }
}


