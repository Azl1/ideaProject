package com.kirillkotov.program;

import com.kirillkotov.model.Cat;
import com.kirillkotov.service.CatService;


public class Main {
    public static void main(String[] args) {
        int life = 9;
        CatService catService = new CatService();
        catService.add(new Cat("Tom", life));
        catService.add(new Cat("Cleocatra", life));
        catService.add(new Cat("Dupli", life));
        catService.add(new Cat("Toodles", life));
        Cat result = catService.start();
        System.out.printf("Кот-победитель: %s!!!%n", result.getName());
    }
}