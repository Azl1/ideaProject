package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Square;
import com.abdullaevaziz.repository.SquareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquareServiceImpl implements SquareService{

    private SquareRepository squareRepository;

    @Autowired
    public void setSquareRepository(SquareRepository squareRepository){
        this.squareRepository = squareRepository;
    }

    @Override
    public List<Square> getAll() {
        return this.squareRepository.findAll();
    }

}
