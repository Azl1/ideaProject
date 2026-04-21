package com.abdullaevaziz.service;


import com.abdullaevaziz.model.TelegramUser;
import com.abdullaevaziz.repository.TelegramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    @Value("${bot.token}")
    private String token;
    private TelegramUserRepository telegramUserRepository;

    @Autowired
    public void setUserRepository(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public TelegramUser add(TelegramUser telegramUser) {
        try {
            return this.telegramUserRepository.save(telegramUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User has already added!");
        }
    }

    @Override
    public TelegramUser get(long id) {
        return telegramUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TelegramUser does not exists!"));
    }

    @Override
    public void updateStep(long id, String step) {
        TelegramUser telegramUserGetId = get(id);
        telegramUserGetId.setStep(step);
        telegramUserRepository.save(telegramUserGetId);
    }

    @Override
    public List<TelegramUser> getList() {
        return this.telegramUserRepository.findAll();
    }
}
