package com.kirillkotov.program;

import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVRepository;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            TVRepository tvRepository = new TVRepository();
            TV resAdd = tvRepository.add(new TV("Samsung", "K900", "black", 10, 20000));
            System.out.println(resAdd);

            List<TV> tvs = tvRepository.get();
            System.out.println(tvs);

            TV tvGet = tvRepository.get(1);
            System.out.println(tvGet);

            TV tvUpdate = tvRepository.update(new TV(1, "ttt", "tt", "t", 1, 1));
            System.out.println(tvUpdate);

            TV tvDelete = tvRepository.delete(1);
            System.out.println(tvDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
