package com.kirillkotov.service;

import com.kirillkotov.model.Dog;

import java.util.List;

public interface DogService {
    void add(Dog dog);

    List<Dog> get();
}
