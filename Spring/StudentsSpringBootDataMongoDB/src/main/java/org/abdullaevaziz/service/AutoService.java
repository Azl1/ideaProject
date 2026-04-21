package org.abdullaevaziz.service;


import org.abdullaevaziz.model.Auto;

import java.util.List;

public interface AutoService {
    void add(Auto auto, String studentId);

    List<Auto> get();

    Auto get(String id);

    Auto delete(String id);


    Auto update(Auto auto);
}
