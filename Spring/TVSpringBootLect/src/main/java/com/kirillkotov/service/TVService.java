package com.kirillkotov.service;

import com.kirillkotov.model.TV;

import java.util.List;

public interface TVService {
    void add(TV tv);

    List<TV> get();

    TV get(long id);

    List<TV> get(String brand);

    TV delete(long id);

    TV update(TV tv);
}
