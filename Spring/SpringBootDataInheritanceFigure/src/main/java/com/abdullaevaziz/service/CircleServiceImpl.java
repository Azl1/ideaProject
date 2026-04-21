package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Circle;
import com.abdullaevaziz.repository.CircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleServiceImpl implements CircleService{
    private CircleRepository circleRepository;

    @Autowired
    public void setCircleRepository(CircleRepository circleRepository){
        this.circleRepository = circleRepository;
    }

    @Override
    public List<Circle> getAll() {
        return this.circleRepository.findAll();
    }

}
