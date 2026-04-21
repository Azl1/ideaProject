package com.kirillkotov.program;

import com.kirillkotov.model.Stock;
import com.kirillkotov.model.TV;
import com.kirillkotov.util.Generator;
import com.kirillkotov.util.ServiceGenerator;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        TV tv = ServiceGenerator.generateTV();
        System.out.println("Print generated TV from ServiceGenerator");
        System.out.println(tv);

        Stock stock = ServiceGenerator.generateStock();
        System.out.println("\nPrint generated Stock from ServiceGenerator");
        System.out.println(stock);

        ArrayList<Stock> stocks = ServiceGenerator.generateStocks();
        System.out.println("\nPrint generated Stocks from ServiceGenerator");
        System.out.println(stocks);

        TV tv2 = Generator.generateTV();
        System.out.println("Print generated TV from Generator");
        System.out.println(tv2);

        Stock stock2 = Generator.generateStock();
        System.out.println("\nPrint generated Stock from Generator");
        System.out.println(stock2);

        ArrayList<Stock> stocks2 = Generator.generateStocks();
        System.out.println("\nPrint generated Stocks from Generator");
        System.out.println(stocks2);
    }
}
