package com.kirillkotov.service;

import com.kirillkotov.model.TV;
import com.kirillkotov.model.User;
import com.kirillkotov.repository.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TVServiceImpl implements TVService {
    private TVRepository tvRepository;
    private UserService userService;

    @Autowired
    public void setTvRepository(TVRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public TV get(long id) {
        return this.tvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TV does not exists!"));
    }

    @Override
    public List<TV> get() {
        return this.tvRepository.findAll();
    }

    @Override
    public void add(long userId, TV tv) {
        User user = this.userService.get(userId);
        tv.setUser(user);
        try {
            this.tvRepository.save(tv);
        } catch (Exception e) { //TODO handle SpringData exception
            throw new IllegalArgumentException("TV has already added!");
        }
    }

    @Override
    public TV update(TV tv) {
        TV base = this.get(tv.getId());
        base.setBrand(tv.getBrand());
        base.setModel(tv.getModel());
        base.setColor(tv.getColor());
        base.setTimeExpectancy(tv.getTimeExpectancy());
        base.setPrice(tv.getPrice());
        try {
            this.tvRepository.save(base);
            return tv;
        } catch (Exception e) { //TODO handle SpringData exception
            throw new IllegalArgumentException("TV is already exists!");
        }
    }

    @Override
    public TV delete(long id) {
        TV tv = get(id);
        this.tvRepository.deleteById(id);
        return tv;
    }
}
