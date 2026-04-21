package com.kirillkotov.program;

import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVRepository;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            TVRepository repository = new TVRepository();
            TV addResult = repository.add(new TV("Samsung", "K900",
                    "Black", 10, 10000));
            System.out.println(addResult);

            TV updateResult = repository.update(new TV(1L, "Panasonic", "E45",
                    "Black", 10, 10000));
            System.out.println(updateResult);

            ArrayList<TV> tvs = repository.get();
            System.out.println(tvs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
