package com.kirillkotov.service;

import com.kirillkotov.model.Pen;
import com.kirillkotov.repository.PenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenServiceImpl implements PenService {
    private PenRepository penRepository;

    @Autowired
    public void setPenRepository(PenRepository penRepository) {
        this.penRepository = penRepository;
    }

    @Override
    public void add(Pen pen) {
        try {
            this.penRepository.save(pen);
        } catch (Exception e) {
            throw new IllegalArgumentException("Pen is already exists!");

        }
    }

    @Override
    public List<Pen> getAll() {
        return this.penRepository.findAll();
    }
}
