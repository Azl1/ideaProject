package com.kirillkotov.service;

import com.kirillkotov.model.User;
import com.kirillkotov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) { //TODO handle SpringData exception
            throw new IllegalArgumentException("User has already added!");
        }
    }

    @Override
    public List<User> get() {
        return this.userRepository.findAll();
        //return this.userRepository.findAll(Sort.by(Sort.Direction.ASC, "sortfield")); с использованием сортировки
    }

    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exists!"));
    }

    @Override
    public User update(User user) {
        User base = this.get(user.getId());
        base.setLogin(user.getLogin());
        base.setFirstName(user.getFirstName());
        base.setLastName(user.getLastName());
        try {
            this.userRepository.save(base);
            return base;
        } catch (Exception e) { //TODO handle SpringData exception
            throw new IllegalArgumentException("User is already exists!");
        }
    }

    @Override
    public User delete(long id) {
        User user = get(id);
        this.userRepository.deleteById(id);
        return user;
    }
}
