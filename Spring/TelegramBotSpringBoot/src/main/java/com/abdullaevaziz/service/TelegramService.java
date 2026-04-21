package com.abdullaevaziz.service;

import com.abdullaevaziz.model.History;
import com.abdullaevaziz.model.TelegramUser;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.web.multipart.MultipartFile;

public interface TelegramService {

    TelegramUser sendMessage(long chatId, String message);
    TelegramUser get(long id);

    TelegramUser deleteMessageChat(long chatId);
    History deleteMessage(long chatId, int messageId);

    TelegramUser sendImage(long chatId, MultipartFile file);

    TelegramUser sendAudio(long chatId, MultipartFile file);

    TelegramUser sendDocument(long chatId, MultipartFile file);

}
