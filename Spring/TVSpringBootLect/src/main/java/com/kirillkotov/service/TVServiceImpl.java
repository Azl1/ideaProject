package com.kirillkotov.service;

import com.kirillkotov.exceptions.ConstraintViolationException;
import com.kirillkotov.model.TV;
import com.kirillkotov.repository.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TVServiceImpl implements TVService {
    private TVRepository tvRepository;

    @Autowired
    public void setTvRepository(TVRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @Override
    public void add(TV tv) {
        try {
            this.tvRepository.save(tv);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("TV has already added!");
        }
    }

    @Override
    public List<TV> get() {
        return this.tvRepository.findAll();
    }

    @Override
    public TV get(long id) {
        return this.tvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TV does not exists!"));
    }

    @Override
    public List<TV> get(String brand) {
        return this.tvRepository.findByBrand(brand);
    }

    @Override
    public TV delete(long id){
        TV tv = this.get(id);
        this.tvRepository.deleteById(id);
        return tv;
    }

    @Override
    public TV update(TV tv) {
        try {
            TV old = this.get(tv.getId());
            old.setBrand(tv.getBrand());
            old.setModel(tv.getModel());
            old.setPrice(tv.getPrice());
            old.setTimeExpectancy(tv.getTimeExpectancy());
            this.tvRepository.save(old);
            return old;
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("TV has already added!");
        }
    }
}
