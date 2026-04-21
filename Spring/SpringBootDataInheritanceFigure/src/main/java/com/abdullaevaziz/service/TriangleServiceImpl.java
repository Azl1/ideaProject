package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Triangle;
import com.abdullaevaziz.repository.TriangleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriangleServiceImpl implements TriangleService{

    private TriangleRepository triangleRepository;

    @Autowired
    public void setTriangleRepository(TriangleRepository triangleRepository){
        this.triangleRepository = triangleRepository;
    }


    @Override
    public List<Triangle> getAll() {
        return this.triangleRepository.findAll();

    }
}
