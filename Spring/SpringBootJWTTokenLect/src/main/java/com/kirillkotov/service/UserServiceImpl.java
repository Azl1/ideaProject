package com.kirillkotov.service;

import com.kirillkotov.model.Role;
import com.kirillkotov.model.Status;
import com.kirillkotov.model.User;
import com.kirillkotov.repository.RoleRepository;
import com.kirillkotov.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void add(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        try {
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User is already exist");
        }
    }

    @Override
    public User addRole(long userId, long roleId) {
        try {
            User user = this.findById(userId);
            Role role = this.roleService.get(roleId);
            user.addRole(role);
            this.userRepository.save(user);
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User is already has such role");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User does not exists"));
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository
                .findById(id).orElseThrow(
                        ()->new IllegalArgumentException("User does not exists"));

        //log.warn("IN findById - no user found by id: {}", id);
        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public User delete(Long id) {
        User user = this.findById(id);
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
        return user;
    }
}
