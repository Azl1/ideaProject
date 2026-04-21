package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Rectangle;
import com.abdullaevaziz.repository.RectangleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectangleServiceImpl implements RectangleService{


    private RectangleRepository rectangleRepository;

    @Autowired
    public void setRectangleRepository(RectangleRepository rectangleRepository){
        this.rectangleRepository = rectangleRepository;
    }

    @Override
    public List<Rectangle> getAll() {
        return this.rectangleRepository.findAll();

    }

}
