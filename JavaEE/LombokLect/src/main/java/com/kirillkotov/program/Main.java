package com.kirillkotov.program;

import com.kirillkotov.model.TV;

public class Main {
    public static void main(String[] args) {
        TV tv1 = new TV();
        TV tv2 = new TV("Samsung", "K900", "Black", 10, 10000);
        TV tv2Copy = new TV("Samsung", "K900", "Black", 10, 10000);
        TV tv3 = new TV("K900", "Black", 10, 10000);

        System.out.println(tv1);
        System.out.println(tv2);
        System.out.println(tv3);

        if(tv2.equals(tv2Copy)){
            System.out.println("+");
        }
        else{
            System.out.println("-");
        }

        //tv1.get
    }
}