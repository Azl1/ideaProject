package com.abdullaevaziz.service;


import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.model.ComplimentsTelegramUsers;
import com.abdullaevaziz.model.TelegramUser;
import com.abdullaevaziz.repository.ComplimentsTelegramUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComplimentsTelegramUsersServiceImpl implements ComplimentsTelegramUsersService {

    private ComplimentsTelegramUsersRepository complimentsTelegramUsersRepository;

    @Autowired
    public void setComplimentsTelegramUsersRepository(ComplimentsTelegramUsersRepository complimentsTelegramUsersRepository) {
        this.complimentsTelegramUsersRepository = complimentsTelegramUsersRepository;
    }

    @Override
    public ComplimentsTelegramUsers saveComplimentForUser(TelegramUser telegramUser, Compliment compliment) {
        try {
            ComplimentsTelegramUsers complimentsTelegramUsers = new ComplimentsTelegramUsers();
            complimentsTelegramUsers.setTelegramUser(telegramUser);
            complimentsTelegramUsers.setCompliment(compliment);
            complimentsTelegramUsers.setCreated(LocalDateTime.now());
            return this.complimentsTelegramUsersRepository.save(complimentsTelegramUsers);
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("ComplimentsTelegramUsers has already added!");
        }
    }
}
