package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Auto;

import java.util.List;
import java.util.Optional;

public interface AutoService {
    void add(Auto auto,long studentId);

    List<Auto> get();

    Auto get(long id);

    List<Auto> getByStudentId(long studentId);

    void removeByStudentId(long studentId);

    List<Auto> get(String brand);

    Auto delete(long id);

    Auto update(Auto auto);
}
