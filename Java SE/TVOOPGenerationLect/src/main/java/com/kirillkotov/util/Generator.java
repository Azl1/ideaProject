package com.kirillkotov.util;

import com.kirillkotov.model.Stock;
import com.kirillkotov.model.TV;

import java.util.ArrayList;

public class Generator {
    public static int generateNumber(int a, int b){
        return a + (int)(Math.random() * (b - a + 1));
    }
    public static TV generateTV(){
        String[] brands = {"Samsung", "Panasonic", "Telefunken", "LG"};
        String[] models = {"Smart TV", "T567", "Tel", "Gnusmas"};
        String[] colors = {"Black", "White"};

        int i = generateNumber(0, brands.length - 1);
        int j = generateNumber(0, colors.length - 1);

        return new TV(brands[i], models[i], colors[j],
                generateNumber(1, 100),
                generateNumber(1, 100000));
    }

    public static Stock generateStock(){
        String[] addresses = {"Moscow", "St-Peterburg", "Kazan", "Ankara"};
        int k = generateNumber(0, addresses.length- 1);
        Stock stock = new Stock(addresses[k]);
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
