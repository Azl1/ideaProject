package com.abdullaevaziz.service;

import com.abdullaevaziz.model.*;
import com.abdullaevaziz.repository.TrainerRepository;
import com.abdullaevaziz.repository.UserRepository;
import com.abdullaevaziz.securety.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    private TrainerRepository trainerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository){
        this.trainerRepository = trainerRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){this.passwordEncoder = passwordEncoder;}

    @Override
    public Trainer add(Trainer trainer) {
        try {
            System.out.println(trainer);
            trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
         return this.trainerRepository.save(trainer);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Trainer has already added!");
        }
    }

    @Override
    public Trainer get(Authentication authentication, long id) throws IllegalAccessException{
        long userId = ((JwtUser) authentication.getPrincipal()).getId();
        User user = this.userRepository.findById(userId).
        orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(user.getClass() != Apprentice.class && user.getClass() != Admin.class && id != userId){
            throw new IllegalAccessException("Trainer does not match user id authentication");
        }
        return this.trainerRepository.findById(id).
        orElseThrow(()->
                new IllegalArgumentException("Trainer does not exists!"));
    }

    @Override
    public List<Trainer> getListTrainer() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Trainer update(Authentication authentication, Trainer trainer) throws IllegalAccessException {
        try {
            Trainer old = this.get(authentication, trainer.getId());
            old.setLogin(trainer.getLogin());
            old.setSurname(trainer.getSurname());
            old.setName(trainer.getName());
            old.setPatronymic(trainer.getPatronymic());

            String password = trainer.getPassword();
            if (!password.equals("***")){
                old.setPassword(passwordEncoder.encode(trainer.getPassword()));
            }

            old.setExperience(trainer.getExperience());
            old.setEmail(trainer.getEmail());
            this.trainerRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Trainer has already added!");
        }
    }

    @Override
    public Trainer delete(Authentication authentication, long id) throws IllegalAccessException {
        Trainer trainerDelete = this.get(authentication, id);
        this.trainerRepository.delete(trainerDelete);
        return trainerDelete;
    }
}
