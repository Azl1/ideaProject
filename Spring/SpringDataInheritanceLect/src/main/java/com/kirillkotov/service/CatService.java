package com.kirillkotov.service;

import com.kirillkotov.model.Cat;

import java.util.List;

public interface CatService {
    void add(Cat cat);

    List<Cat> get();
}
