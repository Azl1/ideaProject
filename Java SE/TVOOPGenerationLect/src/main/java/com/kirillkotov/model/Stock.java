package com.kirillkotov.model;

import java.util.ArrayList;
import java.util.Objects;

public class Stock {
    private String address;
    private ArrayList<TV> arrayList = new ArrayList<>();

    public Stock() {
    }

    public Stock(String address) {
        this.address = address;
    }

    public void add(TV tv){
        this.arrayList.add(tv);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(address, stock.address) && Objects.equals(arrayList, stock.arrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, arrayList);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "address='" + address + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }
}
