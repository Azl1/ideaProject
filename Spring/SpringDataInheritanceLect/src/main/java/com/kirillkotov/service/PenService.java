package com.kirillkotov.service;

import com.kirillkotov.model.Pen;

import java.util.List;

public interface PenService {
    void add(Pen pen);

    List<Pen> getAll();
}
