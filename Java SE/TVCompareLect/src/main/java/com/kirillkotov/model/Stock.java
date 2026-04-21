package com.kirillkotov.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Stock {
    private ArrayList<TV> tvArrayList = new ArrayList<>();

    public void add(TV tv){
        this.tvArrayList.add(tv);
    }

    public void sort(Comparator<TV> tvComparator){
        this.tvArrayList.sort(tvComparator);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "tvArrayList=" + tvArrayList +
                '}';
    }
}
