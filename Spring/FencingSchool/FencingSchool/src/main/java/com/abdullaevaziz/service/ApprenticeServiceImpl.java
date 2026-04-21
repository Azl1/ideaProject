package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Apprentice;
import com.abdullaevaziz.repository.ApprenticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenticeServiceImpl implements ApprenticeService{

    private ApprenticeRepository apprenticeRepository;
    private UserService userService;

    @Autowired
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository){
        this.apprenticeRepository = apprenticeRepository;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }



    @Override
    public void add(Apprentice apprentice) {
        try {
            this.apprenticeRepository.save(apprentice);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice has already added!");
        }
    }

    @Override
    public List<Apprentice> get() {
        return this.apprenticeRepository.findAll();
    }

    @Override
    public Apprentice get(long id) {
        return this.apprenticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apprentice does not exists!"));
    }

    @Override
    public Apprentice update(Apprentice apprentice) {
        try {
            Apprentice old = this.get(apprentice.getId());
            old.setSurname(apprentice.getSurname());
            old.setName(apprentice.getName());
            old.setPatronymic(apprentice.getPatronymic());
            old.setPhoneNumber(apprentice.getPhoneNumber());

            this.apprenticeRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice has already added!");
        }
    }

    @Override
    public Apprentice delete(long id) {
        Apprentice apprentice = this.get(id);
        this.apprenticeRepository.deleteById(id);
        return apprentice;
    }
}
