package com.kirillkotov.program;

import com.kirillkotov.model.TV;
import com.kirillkotov.repository.StockRepository;
import com.kirillkotov.repository.TVRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        /**
         * Dom parser for XML tags work
         */
        try {
            TVRepository tvRepositoryDom = new TVRepository("tv.xml");
            System.out.println(tvRepositoryDom);

            TVRepository repository = new TVRepository(
                    new TV("Samsung", "K900", "Black", 10, 20000));
            repository.save("tv_result.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            StockRepository stockRepositoryDom = new StockRepository("stock.xml");
            System.out.println(stockRepositoryDom);
            stockRepositoryDom.add(new TV("Samsung", "K900", "Black", 10, 20000));
            stockRepositoryDom.add(new TV("Panasonic", "E67", "White", 5, 20000));
            stockRepositoryDom.save("stock_result.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /**
         * Jackson Parser for XML serialize and deserialize Java objects
         */
        try {
            TVRepository tvRepository1 = new TVRepository(
                    new TV("Samsung", "K900", "Black", 10, 20000));
            tvRepository1.save("tv2.xml");

            TVRepository tvRepository2 = new TVRepository("tv2.xml");
            System.out.println(tvRepository2);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            StockRepository stockRepository = new StockRepository();
            stockRepository.setAddress("Moscow");
            stockRepository.add(new TV("Samsung", "K900", "Black", 10, 20000));
            stockRepository.add(new TV("Panasonic", "T45", "White", 10, 30000));
            stockRepository.add(new TV("Telefunken", "E45", "Black", 20, 20000));
            stockRepository.save("stock2.xml");

            StockRepository stockRepository2 = new StockRepository("stock2.xml");
            System.out.println(stockRepository2);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}