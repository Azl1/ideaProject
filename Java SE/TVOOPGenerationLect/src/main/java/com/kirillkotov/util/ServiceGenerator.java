package com.kirillkotov.util;

import com.kirillkotov.model.Stock;
import com.kirillkotov.model.TV;

import java.util.ArrayList;

public class ServiceGenerator {
    public static int generateNumber(int a, int b){
        return a + (int)(Math.random() * (b - a + 1));
    }
    public static TV generateTV(){
        return new TV("B" + generateNumber(1, 100),
                "M" + generateNumber(1, 100),
                "C" + generateNumber(1, 100),
                generateNumber(1, 100),
                generateNumber(1, 100000));
    }

    public static Stock generateStock(){
        Stock stock = new Stock("A" + generateNumber(1, 20));
        int n = generateNumber(1, 5);
        for (int i = 0; i < n; i++) {
            TV tv = generateTV();
            stock.add(tv);
        }
        return stock;
    }

    public static ArrayList<Stock> generateStocks(){
        ArrayList<Stock> stocks = new ArrayList<>();
        int n = generateNumber(1, 5);
        for (int i = 0; i < n; i++) {
            Stock stock = generateStock();
            stocks.add(stock);
        }
        return stocks;
    }
}
