package com.kirillkotov.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.Objects;

public class Stock {
    private String address;
    @JacksonXmlElementWrapper(localName = "list")
    @JacksonXmlProperty(localName = "tv")
    private ArrayList<TV> list = new ArrayList<>();

    public Stock() {
    }

    public Stock(String address) {
        this.address = address;
    }

    public void add(TV tv){
        this.list.add(tv);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<TV> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(address, stock.address) && Objects.equals(list, stock.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, list);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "address='" + address + '\'' +
                ", list=" + list +
                '}';
    }
}
