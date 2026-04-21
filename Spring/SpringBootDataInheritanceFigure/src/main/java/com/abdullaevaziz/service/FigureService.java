package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.model.Figure;

import java.util.List;

public interface FigureService {

    List<Figure> getAll();

    Figure add(Figure figure);
    Figure get(long id);
    Figure delete(long id);
    Figure update(Figure figure);
}
