package com.abdullaevaziz.service;


import com.abdullaevaziz.model.TelegramUser;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TelegramUserService {

    TelegramUser add(TelegramUser telegramUser);

    TelegramUser get(long id);

    List<TelegramUser> getList();

    void updateStep(long id, String login);

}
