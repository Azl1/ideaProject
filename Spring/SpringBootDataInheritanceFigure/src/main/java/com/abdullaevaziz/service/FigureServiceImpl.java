package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.model.Figure;
import com.abdullaevaziz.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FigureServiceImpl implements FigureService{

    private FigureRepository figureRepository;

    @Autowired
    public void setFigureRepository(FigureRepository figureRepository){
        this.figureRepository = figureRepository;
    }

    @Override
    public List<Figure> getAll() {
        return this.figureRepository.findAll();
    }

    @Override
    public Figure add(Figure figure) {
        try {
            return   this.figureRepository.save(figure);
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Figure has already added!");
        }
    }

    @Override
    public Figure get(long id) {
        return this.figureRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Figure does not exists!"));
    }

    @Override
    public Figure delete(long id) {
        Figure figure = this.get(id);
        this.figureRepository.deleteById(id);
        return figure;
    }

    @Override
    public Figure update(Figure newfigure) {
        try {
            Figure old = this.get(newfigure.getId());
            if (!old.getClass().equals(newfigure.getClass())) {
                throw new ClassCastException("Объекты разных классов!");
            }

            ArrayList<Field> arrayList = new ArrayList<>(List.of(Figure.class.getDeclaredFields()));
            Field[] fields = old.getClass().getDeclaredFields();
            List<Field> list = Arrays.asList(fields);
            arrayList.addAll(list);
            for (Field field : arrayList) {
                field.setAccessible(true);
                Object fieldValue = field.get(newfigure);
                field.set(old, fieldValue);
            }

            this.figureRepository.save(old);
            return old;

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Figure has already added!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
