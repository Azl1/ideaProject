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
        } catch (Exception e) { //TODO Handle Mongo exception
            throw new IllegalArgumentException("User has already added!");
        }
    }

    @Override
    public List<User> get() {
        return this.userRepository.findAll();
    }

    @Override
    public User get(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
    }

    @Override
    public List<User> getAllByFirstName(String firstName) {
        return this.userRepository.findAllByFirstName(firstName);
    }

    @Override
    public User getByLogin(String login) {
        return this.userRepository.findUserByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
    }

    @Override
    public User delete(String id) {
        User user = this.get(id);
        this.userRepository.deleteById(id);
        return user;
    }

    @Override
    public User update(User user) {
        User userOld = this.get(user.getId());
        userOld.setFirstName(user.getFirstName());
        userOld.setLastName(user.getLastName());
        userOld.setLogin(user.getLogin());
        try {
            this.userRepository.save(userOld);
            return userOld;
        } catch (Exception e) { //TODO handle Mongo exception
            throw new IllegalArgumentException("User already exists!");
        }
    }

    @Override
    public User addTvs(User user) {
        User userOld = this.get(user.getId());
        userOld.setFirstName(user.getFirstName());
        userOld.setLastName(user.getLastName());
        userOld.setLogin(user.getLogin());
        userOld.setTvs(user.getTvs());
        try {
            this.userRepository.save(userOld);
            return userOld;
        } catch (Exception e) {//TODO handle Mongo exception
            throw new IllegalArgumentException("User already exists!");
        }
    }
}
