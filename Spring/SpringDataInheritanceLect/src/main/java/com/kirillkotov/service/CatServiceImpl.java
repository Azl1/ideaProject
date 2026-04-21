package com.kirillkotov.service;

import com.kirillkotov.model.Cat;
import com.kirillkotov.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService{
    private CatRepository catRepository;

    @Autowired
    public void setCatRepository(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public void add(Cat cat){
        try {
            this.catRepository.save(cat);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cat is already exists!");
        }
    }

    @Override
    public List<Cat> get(){
        return this.catRepository.findAll();
    }
}
