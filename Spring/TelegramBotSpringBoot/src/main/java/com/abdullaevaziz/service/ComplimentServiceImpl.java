package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.model.ComplimentsTelegramUsers;
import com.abdullaevaziz.repository.ComplimentRepository;
import com.abdullaevaziz.repository.ComplimentsTelegramUsersRepository;
import com.abdullaevaziz.util.UtilRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComplimentServiceImpl implements ComplimentService {

    private ComplimentRepository complimentRepository;
    private ComplimentsTelegramUsersRepository complimentsTelegramUsersRepository;

    @Autowired
    public void setComplimentRepository(ComplimentRepository complimentRepository) {
        this.complimentRepository = complimentRepository;
    }

    @Autowired
    public void setComplimentsTelegramUsersRepository(ComplimentsTelegramUsersRepository complimentsTelegramUsersRepository) {
        this.complimentsTelegramUsersRepository = complimentsTelegramUsersRepository;
    }

    @Override
    public Compliment add(Compliment compliment) {
        try {
            return this.complimentRepository.save(compliment);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Compliment has already added!");
        }
    }

    @GetMapping
    public List<Compliment> getList() {
        return complimentRepository.findAll();
    }

    @Override
    public Compliment getById(long id) {
        return this.complimentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Compliment does not exists!"));
    }


    @Override
    @Transactional
    public Compliment getComplimentRandom(long idUser) {

        List<ComplimentsTelegramUsers> getCompUsers = complimentsTelegramUsersRepository.findAllByTelegramUserId(idUser);
        List<Long> indexes = getCompUsers.stream().map(ComplimentsTelegramUsers::getId).collect(Collectors.toList());

        List<Compliment> complimentList = getList();

        if (indexes.size() == complimentList.size()) {
            this.complimentsTelegramUsersRepository.deleteAllByTelegramUserId(idUser);
            indexes.clear();
        }

        Compliment compliment;
        do {
            int i = UtilRandom.getRandom(0, complimentList.size() - 1);

             compliment = complimentList.get(i);
        }
        while (indexes.contains(compliment.getId()));

        return compliment;

    }
}
