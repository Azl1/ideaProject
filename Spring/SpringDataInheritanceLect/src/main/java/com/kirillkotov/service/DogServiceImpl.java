package com.kirillkotov.service;

import com.kirillkotov.model.Dog;
import com.kirillkotov.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService{
    private DogRepository dogRepository;

    @Autowired
    public void setDogRepository(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void add(Dog dog){
        try {
            this.dogRepository.save(dog);
        } catch (Exception e) {
            throw new IllegalArgumentException("Dog is already exists!");
        }
    }

    @Override
    public List<Dog> get(){
        return this.dogRepository.findAll();
    }
}
