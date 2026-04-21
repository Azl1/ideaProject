package com.kirillkotov.service;

import com.kirillkotov.model.User;
import com.kirillkotov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User get(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exists!"));
    }

    @Override
    public User addUser(User user) {
        try {
            user.setRegDate(LocalDate.now());
            return this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("User is already exists");
        }
    }

    @Override
    public User delete(Long id) {
        User user = this.get(id);
        this.userRepository.deleteById(id);
        return user;
    }

    @Override
    public List<User> delete(String name) {
        List<User> users = this.userRepository.deleteAllByName(name);
        if (users.size() == 0) {
            throw new IllegalArgumentException("Users with current name does not exist!");
        }
        return users;
    }

    @Override
    public User updateUser(User user) {
        User updated = this.get(user.getId());
        try {
            updated.setLogin(user.getLogin());
            updated.setPassword(user.getPassword());
            updated.setName(user.getName());
            return this.userRepository.save(updated);
        } catch (Exception e) {
            throw new IllegalArgumentException("User is already exists");
        }
    }
}
