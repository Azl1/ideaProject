package com.abdullaevaziz.service;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.ApprenticeRepository;
import com.abdullaevaziz.repository.UserRepository;
import com.abdullaevaziz.securety.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenticeServiceImpl implements ApprenticeService{
    private ApprenticeRepository apprenticeRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAdminRepository(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;}

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Apprentice add(Apprentice apprentice) {
        try {
            apprentice.setPassword(passwordEncoder.encode(apprentice.getPassword()));
           return this.apprenticeRepository.save(apprentice);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice has already added!");
        }
    }

    @Override
    public Apprentice get(Authentication authentication, long id) throws IllegalAccessException {
        long userId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(user.getClass() != Trainer.class && user.getClass() != Admin.class && id != userId){
            throw new IllegalAccessException("Apprentice does not match user id authentication");
        }
        return this.apprenticeRepository.
                findById(id).orElseThrow(() -> new IllegalArgumentException("Apprentice does not exists!"));
    }

    @Override
    public List<Apprentice> getListApprentice() {
        return this.apprenticeRepository.findAll();
    }

    @Override
    public Apprentice update(Authentication authentication, Apprentice apprentice)
            throws IllegalAccessException {
        try {
            Apprentice old = this.get(authentication, apprentice.getId());
            old.setLogin(apprentice.getLogin());
            old.setSurname(apprentice.getSurname());
            old.setName(apprentice.getName());
            old.setPatronymic(apprentice.getPatronymic());

            String password = apprentice.getPassword();
            if (!password.equals("***")){
                old.setPassword(passwordEncoder.encode(apprentice.getPassword()));
            }

            old.setPhoneNumber(apprentice.getPhoneNumber());
            this.apprenticeRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice has already added!");
        }
    }

    @Override
    public Apprentice delete(Authentication authentication, long id) throws IllegalAccessException {
        Apprentice apprenticeDelete = this.get(authentication, id);
        this.apprenticeRepository.delete(apprenticeDelete);
        return apprenticeDelete;
    }
}
