package com.kirillkotov.program;


import com.kirillkotov.model.Size;
import com.kirillkotov.model.TV;

public class Main {
    public static void main(String[] args) {
        TV a = new TV();
        TV b = new TV("Samsung", "K90", "Black", 10, 10000);
        TV c = new TV("Samsung", "K90", "Black", 20, 10000);
        TV d = new TV("Samsung", "K90");
        Size size = new Size(1,2,3);
        TV e =  new TV("Samsung", "K90", "Black", 10, 10000, size);

        System.out.println("Print TV a\n" + a);
        System.out.println("\nPrint TV b\n" + b);
        System.out.println("\nPrint TV c\n" + c);
        System.out.println("\nPrint TV d\n" + d);
        System.out.println("\nPrint TV e\n" + e);

        String r = a + "";

        int timeExpectancyB = b.getTimeExpectancy();
        System.out.println("\nTime expectancy TV b");
        System.out.println(timeExpectancyB);

        System.out.println("\nTime expectancy TV c");
        int timeExpectancyC = c.getTimeExpectancy();
        System.out.println(timeExpectancyC);

        b.setColor("White");
        System.out.println("\nTV b after setter invoke");
        System.out.println(b);

        double v1 = b.evalPrice(1000);
        System.out.println(v1);
        System.out.println("\nTV b after evalPrice invoke");
        System.out.println(b);

        double v2 = b.evalPrice(1000.0);
        System.out.println(v2);
        System.out.println("\nTV b after evalPrice invoke");
        System.out.println(b);

        double v3 = b.evalPrice("1000");
        System.out.println(v3);
        System.out.println("\nTV b after evalPrice invoke");
        System.out.println(b);

        TV f = new TV("Samsung", "K90", "Black", 10, 10000);
        TV g = new TV("Samsung", "K90", "Black", 10, 10000);
        if(f == g){
            System.out.println("\nResult Incorrect work");
        }

        if(f.equals(g)){
            System.out.println("\nResult of Equals");
        }
        else{
            System.out.println("\nResult of Not Equals");
        }

        TV[] mass = {b, c};
        double v = TV.avgPrice(mass);
        System.out.println(v);

        System.out.println(TV.TV_ID);
        System.out.println(TV.BRAND_COMMON);

        int count = TV.getCount();
        System.out.println("\nResult of count created objects");
        System.out.println(count);
    }
}