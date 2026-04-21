package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Trainer;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.TrainerRepository;
import com.abdullaevaziz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    private TrainerRepository trainerRepository;
    private UserService userService;

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository){
        this.trainerRepository = trainerRepository;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }



    @Override
    public void add(Trainer trainer) {
        try {
            this.trainerRepository.save(trainer);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Trainer has already added!");
        }
    }

    @Override
    public List<Trainer> get() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Trainer get(long id) {
        return this.trainerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trainer does not exists!"));
    }

    @Override
    public Trainer update(Trainer trainer) {
        try {
            Trainer old = this.get(trainer.getId());
            old.setSurname(trainer.getSurname());
            old.setName(trainer.getName());
            old.setPatronymic(trainer.getPatronymic());
            old.setExperience(trainer.getExperience());

            this.trainerRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Trainer has already added!");
        }
    }

    @Override
    public Trainer delete(long id) {
        Trainer trainer = this.get(id);
        this.trainerRepository.deleteById(id);
        return trainer;
    }

}
