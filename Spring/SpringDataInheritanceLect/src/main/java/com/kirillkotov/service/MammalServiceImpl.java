package com.kirillkotov.service;

import com.kirillkotov.model.Mammal;
import com.kirillkotov.repository.MammalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
public class MammalServiceImpl implements MammalService {
    private MammalRepository mammalRepository;

    @Autowired
    public void setMammalRepository(MammalRepository mammalRepository) {
        this.mammalRepository = mammalRepository;
    }

    @Override
    public List<Mammal> get() {
        return this.mammalRepository.findAll();
    }

    @Override
    public Mammal get(long id) {
        return this.mammalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mammal does not exists!"));
    }

    @Override
    public Mammal add(Mammal mammal) {
        try {
            return this.mammalRepository.save(mammal);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Mammal has already added!");
        }
    }

    @Override
    public Mammal delete(long id) {
        Mammal mammal = this.get(id);
        this.mammalRepository.deleteById(id);
        return mammal;
    }


    @Override
    public Mammal update(Mammal newMammal) {
        try {
            Mammal old = this.get(newMammal.getId());
            if (!old.getClass().equals(newMammal.getClass())) {
                throw new ClassCastException("Объекты разных классов!");
            }

            ArrayList<Field> arrayList = new ArrayList<>(List.of(Mammal.class.getDeclaredFields()));
            Field[] fields = old.getClass().getDeclaredFields();
            List<Field> list = Arrays.asList(fields);
            arrayList.addAll(list);
            for (Field field : arrayList) {
                field.setAccessible(true);
                Object fieldValue = field.get(newMammal);
                field.set(old, fieldValue);
            }

            this.mammalRepository.save(old);
            return old;

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Mammal has already added!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
