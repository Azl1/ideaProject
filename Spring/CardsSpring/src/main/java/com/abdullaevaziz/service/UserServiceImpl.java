package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("User has already added!");
        }
    }

    @Override
    public List<User> get() {
        return this.userRepository.findAll();
        //return this.userRepository.findAll(Sort.by(Sort.Direction.ASC, "sortfield")); с использованием сортировки
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exists!"));
    }

    @Override
    public User get(String login, String password) {
        return this.userRepository.findAllByLoginAndPassword(login, password);
    }

    @Override
    public User update(User user) {
        User base = this.get(user.getId());
        base.setLogin(user.getLogin());
        base.setPassword(user.getPassword());
        base.setName(user.getName());
        try {
            this.userRepository.save(base);
            return base;
        } catch (DataIntegrityViolationException e) {
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
