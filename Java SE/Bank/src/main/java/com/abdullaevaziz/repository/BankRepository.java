package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Bank;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;

public class BankRepository {
    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    private Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * • Конструктор с именем файла базы данных
     */
    public BankRepository(String fileName) throws IOException {
        this.bank = objectMapper.readValue(new File(fileName), Bank.class);
    }

    /**
     * • Конструктор с Банком для сохранения в базу данных
     */
    public BankRepository(Bank bank) {
        this.bank = bank;
    }

    /**
     * • Геттер для получения загруженного банка из базы данных
     */
    public Bank getLoadedBank() {
        return this.bank;
    }

    /**
     * • Метод сохранения банка в базу данных
     */
    public void saveBank(String fileName) throws IOException {
        this.objectMapper.writeValue(new File(fileName), bank);
    }

    /**
     * • Метод удаления банка из базы данных
     */
    public void removeBank(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("");
        }
    }

    @Override
    public String toString() {
        return "BankRepository{" +
                "bank=" + bank +
                '}';
    }
}
