package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Auto;

import java.util.List;

public interface AutoService {
    void add(Auto auto, long studentId);

    List<Auto> get();

    Auto get(long id);

    Auto delete(long id);


    Auto update(Auto auto);
}
