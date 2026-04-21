package com.kirillkotov.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kirillkotov.model.Stock;
import com.kirillkotov.model.TV;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StockRepository {
    private Stock stock = new Stock();

    public StockRepository() {
    }

    public StockRepository(Stock stock) {
        this.stock = stock;
    }

    public StockRepository(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        this.stock = xmlMapper.readValue(new File(fileName), Stock.class);
    }

    public Stock getStock() {
        return stock;
    }

    public void setAddress(String address) {
        this.stock.setAddress(address);
    }

    public void add(TV tv){
        this.stock.add(tv);
    }

    public void save(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlMapper.writeValue(bufferedWriter, this.stock);
        }
    }

    @Override
    public String toString() {
        return "StockRepository{" +
                "stock=" + stock +
                '}';
    }
}
