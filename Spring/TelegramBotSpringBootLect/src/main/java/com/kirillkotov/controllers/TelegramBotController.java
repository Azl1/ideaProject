package com.kirillkotov.controllers;

import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendAudio;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.File;

@BotController
public class TelegramBotController implements TelegramMvcController {
    @Value("${bot.token}")
    private String token;
    private Keyboard replyKeyboardMarkup;

    @PostConstruct
    public void init(){
        this.replyKeyboardMarkup = new ReplyKeyboardMarkup(
                "/image", "/audio")
                .oneTimeKeyboard(true)   // optional
                .resizeKeyboard(true)    // optional
                .selective(true);
    }

    @Override
    public String getToken() {
        return this.token;
    }

    private SendMessage sendMessageWithButtons(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(replyKeyboardMarkup);
        return sendMessage.parseMode(ParseMode.HTML);
    }

    private SendPhoto sendImageWithButtons(long chatId, String fileName) {
        SendPhoto sendPhoto = new SendPhoto(chatId, new File(fileName));
        sendPhoto.replyMarkup(replyKeyboardMarkup);
        return sendPhoto.parseMode(ParseMode.HTML);
    }

    private SendAudio sendAudioWithButtons(long chatId, String fileName) {
        SendAudio sendAudio = new SendAudio(chatId, new File(fileName));
        sendAudio.replyMarkup(replyKeyboardMarkup);
        return sendAudio.parseMode(ParseMode.HTML);
    }

    /**
     * Callback for /start message
     * @param user from bot
     * @param chat from bot
     * @return message to client
     */
    @BotRequest(value = "/start", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest start(User user, Chat chat) {
        //TODO handle chatId
        long chatId = chat.id();
        System.out.println(chatId);
        return sendMessageWithButtons(chat.id(), "Hello! \uD83D\uDD25 Welcome to <b>my</b> bot!");
    }

    /**
     * Callback for /image message
     * @param user from bot
     * @param chat from bot
     * @return image to client
     */
    @BotRequest(value = "/image", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest image(User user, Chat chat) {
        return sendImageWithButtons(chat.id(), "12.png");
    }

    /**
     * Callback for /audio message
     * @param user from bot
     * @param chat from bot
     * @return message to client
     */
    @BotRequest(value = "/audio", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest audio(User user, Chat chat) {
        return sendAudioWithButtons(chat.id(), "2.mp3");
    }

    /**
     * Callback for other messages
     * @param text from bot
     * @param user from bot
     * @param chat from bot
     * @return message to client
     */
    @BotRequest(value = "{message:[\\S ]+}", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest all(@BotPathVariable("message") String text, User user, Chat chat) {
         return sendMessageWithButtons(chat.id(), "Thank you for message, you message: " + text);
    }
}
