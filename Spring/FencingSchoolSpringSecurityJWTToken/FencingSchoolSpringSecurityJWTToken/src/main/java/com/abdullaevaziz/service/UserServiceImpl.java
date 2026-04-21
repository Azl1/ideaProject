package com.abdullaevaziz.service;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /*@Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }*/

    @Override
    public User getAuthenticatedUser(Authentication authentication) {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + login));
       if (!passwordEncoder.matches(password, user.getPassword())){
           throw new UsernameNotFoundException("Incorrect password user");
       }
        return user;
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User delete(long id) {
        User userDelete = this.get(id);
        this.userRepository.delete(userDelete);
        return userDelete;
    }

    @Override
    public User findByUsername(String login) {
        User result = userRepository.findByLogin(login)
                .orElseThrow(()->new UsernameNotFoundException("User does not exists"));
        log.info("IN findByUsername - admin: {} found by username: {}", result, login);
        return result;
    }
}
