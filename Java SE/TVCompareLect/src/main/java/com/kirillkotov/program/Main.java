package com.kirillkotov.program;


import com.kirillkotov.model.Size;
import com.kirillkotov.model.Stock;
import com.kirillkotov.model.TV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Size size1 = new Size(1,2,3);
        Size size2 = new Size(2,3, 4);

        if(size1.compareTo(size2) < 0){
            System.out.println("size1 < size2");
        }
        else if(size1.compareTo(size2) > 0){
            System.out.println("size1 > size2");
        }
        else{
            System.out.println("size1 = size2");
        }

        TV tv1 = new TV("Samsung", "K900", "Black", 10, 10000, size1);
        TV tv2 = new TV("Samsung", "K900", "Black", 10, 10000, size2);
        if(tv1.compareTo(tv2) < 0){
            System.out.println("tv1 < tv2");
        }
        else if(tv1.compareTo(tv2) > 0){
            System.out.println("tv1 > tv2");
        }
        else{
            System.out.println("tv1 == tv2");
        }

        Comparator<TV> tvComparator1 = new Comparator<TV>() {
            @Override
            public int compare(TV o1, TV o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        };
        if(tvComparator1.compare(tv1, tv2) < 0){
            System.out.println("tv1 < tv2");
        }
        else if(tvComparator1.compare(tv1, tv2) > 0){
            System.out.println("tv1 > tv2");
        }
        else{
            System.out.println("tv1 = tv2");
        }

        Comparator<TV> tvComparator2 = new Comparator<TV>() {
            @Override
            public int compare(TV o1, TV o2) {
                return -Integer.compare(o1.getTimeExpectancy(), o2.getTimeExpectancy());
            }
        };
        if(tvComparator2.compare(tv1, tv2) < 0){
            System.out.println("tv1 < tv2");
        }
        else if(tvComparator2.compare(tv1, tv2) > 0){
            System.out.println("tv1 > tv2");
        }
        else{
            System.out.println("tv1 = tv2");
        }

        TV[] mass = new TV[]{
                new TV("Samsung", "K900", "Black", 10, 10000,
                        new Size(3,5,7)),
                new TV("Panasonic", "K900", "Black", 7, 20000,
                        new Size(1,2,3)),
                new TV("Telefunken", "K900", "Black", 5, 10000,
                        new Size(1,2,3))
        };

        Arrays.sort(mass);
        System.out.println(Arrays.toString(mass));

        Arrays.sort(mass, tvComparator1);
        System.out.println(Arrays.toString(mass));

        Arrays.sort(mass, tvComparator2);
        System.out.println(Arrays.toString(mass));

        ArrayList<TV> tvArrayList = new ArrayList<>();
        tvArrayList.add(new TV("Telefunken", "K900", "Black", 5, 10000,
                new Size(1,2,3)));
        tvArrayList.add(new TV("Telefunken", "K900", "Black", 5, 10000,
                new Size(1,2,3)));
        tvArrayList.sort(null);
        tvArrayList.sort(tvComparator1);

        Stock stock = new Stock();
        stock.add(tv1);
        stock.add(tv2);
        stock.sort(null);
        System.out.println(stock);
        stock.sort(tvComparator1);
        System.out.println(stock);
    }
}