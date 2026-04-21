package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserType;
import com.abdullaevaziz.repository.UserRepository;
import com.abdullaevaziz.securety.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private FileSystemService fileSystemService;

    @Autowired
    public void setFileSystemService(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 1. Регистрация нового пользователя(после успешной регистрации создается
     * в папке юзерфайлс папка с айдишником созданного юзера)
     */
    @Override
    public void addUser(User user, UserType userType) {
        add(user, userType, "User has already added!");
    }

    @Override
    public void addAdmin(User user, UserType userType) {
        add(user, userType, "Admin has already added!");
    }

    private void add(User user, UserType userType, String s) {
        try {
            user.setUserType(userType);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);
            this.fileSystemService.createBaseUserDir(user.getId());
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(s);
        }
    }


    @Override
    public User findByUsername(String login) {
        User result = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        log.info("IN findByUsername - admin: {} found by username: {}", result, login);
        return result;
    }

    @Override
    public User get(long id) {
        //long userId = ((JwtUser) authentication.getPrincipal()).getId();
        return this.userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    @Override
    public List<User> getListUsers(Authentication authentication) {
        long userId = ((JwtUser) authentication.getPrincipal()).getId();
        return this.userRepository.findAll();
    }

}
