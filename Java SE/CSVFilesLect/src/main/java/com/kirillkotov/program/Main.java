package com.kirillkotov.program;

import com.kirillkotov.model.Size;
import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TVRepository tvRepository = new TVRepository("tvs.csv");
            System.out.println(tvRepository);

            tvRepository.add(new TV("LG", "E45", "White", 10, 1000,
                    new Size(1,2,3)));
            tvRepository.save("out.csv");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}