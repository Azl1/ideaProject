package com.kirillkotov.service;

import com.kirillkotov.model.TV;

import java.util.List;


public interface TVService {
    void add(long userId, TV tv);
    List<TV> get();
    TV get(long id);
    TV update(TV tv);
    TV delete(long id);
}
