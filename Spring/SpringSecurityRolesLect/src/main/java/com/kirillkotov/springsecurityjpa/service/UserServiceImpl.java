package com.kirillkotov.springsecurityjpa.service;

import com.kirillkotov.springsecurityjpa.model.Role;
import com.kirillkotov.springsecurityjpa.model.User;
import com.kirillkotov.springsecurityjpa.repository.RoleRepository;
import com.kirillkotov.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    private RoleService roleService;
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addRole(Long userId, Long roleId) {
        try {
            User userIdGet = this.userService.get(userId);
            Role roleIdGet =  this.roleService.get(roleId);
            userIdGet.add(roleIdGet);
            return userRepository.save(userIdGet);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Role has already exists ");
        }
    }

    @Override
    public User add(User user) {
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User has already exists");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
    }
}
