package com.abdullaevaziz.service;

import com.abdullaevaziz.model.History;
import com.abdullaevaziz.model.TelegramUser;
import com.abdullaevaziz.repository.HistoryRepository;
import com.abdullaevaziz.repository.TelegramUserRepository;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TelegramServiceImpl implements TelegramService {
    @Value("${bot.token}")
    private String token;

    private HistoryService historyService;
    private HistoryRepository historyRepository;
    private TelegramUserService telegramUserService;
    private TelegramUserRepository telegramUserRepository;

    @Autowired
    public void setHistoryService(HistoryService historyService){
        this.historyService = historyService;
    }

    @Autowired
    public void setTelegramService(TelegramUserService telegramUserService){
        this.telegramUserService = telegramUserService;
    }

    @Autowired
    public void setHistoryRepository(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    @Autowired
    public void setTelegramUserRepository(TelegramUserRepository telegramUserRepository){
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public TelegramUser sendMessage(long chatId, String message) {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new SendMessage(chatId, message),
                new Callback<SendMessage, SendResponse>() {
                    @Override
                    public void onResponse(SendMessage sendMessage,
                                           SendResponse sendResponse) {
                        int messageId = sendResponse.message().messageId();

                        //TODO handle messageId
                        System.out.println(messageId);
                        TelegramUser telegramUserGet = telegramUserService.get(chatId);
                        History history1 = new History(telegramUserGet, "sendMessage", messageId);
                        historyService.saveHistory(history1);
                    }

                    @Override
                    public void onFailure(SendMessage sendMessage, IOException e) {
                        System.out.println(sendMessage);
                        e.printStackTrace();
                    }
                });
        TelegramUser user = new TelegramUser();
        user.setId(chatId);
        return user;
    }

    @Override
    public TelegramUser get(long chatId) {
        return telegramUserRepository.findById(chatId).orElseThrow(() -> new IllegalArgumentException("TelegramUser chatId does not exists!"));
    }

    @Override
    public History deleteMessage(long chatId, int messageId) {
        // TODO для этого етода в контроллере где ты делал отправку файлов и тд написать метод
        // делете который в пафварибле принимает чатайди и мессадж айди и производит удаления сообщения(хистори) как из базы так и из бота
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMessage(chatId, messageId));

        //TODO удалить хзитори для этгот мессадж айди из базы и вернуть этот хистори клиенту
        History historyGet = this.historyService.findByTelegramUserIdAndTelegramMessageId(chatId, messageId);
        this.historyRepository.delete(historyGet);
        return historyGet;
    }

    @Override
    public TelegramUser deleteMessageChat(long chatId) {
        List<History> histories = this.historyService.findAllByTelegramUserIdList(chatId);
        for (History history : histories) {
            deleteMessage(chatId, (int) history.getTelegramMessageId());
        }

        TelegramUser telegramUserGet = this.telegramUserService.get(chatId);
        this.telegramUserRepository.delete(telegramUserGet);
        return telegramUserGet;
    }

    @Override
    public TelegramUser sendImage(long chatId, MultipartFile file) {
        String name = file.getOriginalFilename();
        TelegramUser user = new TelegramUser();
        user.setId(chatId);

        try (BufferedOutputStream bufferedOutputStream =
                     new BufferedOutputStream(new FileOutputStream(name))) {
            bufferedOutputStream.write(file.getBytes());

            TelegramBot bot = new TelegramBot(token);
            bot.execute(new SendPhoto(chatId, new File(name)), new Callback<SendPhoto, SendResponse>() {

                @Override
                public void onResponse(SendPhoto sendPhoto, SendResponse sendResponse) {
                  //  int messageId = sendResponse.message().messageId();

                    //TODO handle messageId
              //      System.out.println(messageId);

               //     System.out.println(sendResponse);
                    int messageId = sendResponse.message().messageId();
                    TelegramUser telegramUserGet = telegramUserService.get(chatId);
                    History history1 = new History(telegramUserGet, "sendImage", messageId);
                    historyService.saveHistory(history1);
                }

                @Override
                public void onFailure(SendPhoto sendPhoto, IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        return user;
    }

    @Override
    public TelegramUser sendAudio(long chatId, MultipartFile file) {
        String name = file.getOriginalFilename();
        TelegramUser user = new TelegramUser();
        user.setId(chatId);
        try (BufferedOutputStream bufferedOutputStream =
                     new BufferedOutputStream(new FileOutputStream(name))) {
            bufferedOutputStream.write(file.getBytes());

            TelegramBot bot = new TelegramBot(token);
            bot.execute(new SendAudio(chatId, new File(name)), new Callback<SendAudio, SendResponse>() {

                @Override
                public void onResponse(SendAudio sendAudio, SendResponse sendResponse) {
                    int messageId = sendResponse.message().messageId();
                    TelegramUser telegramUserGet = telegramUserService.get(chatId);
                    History history1 = new History(telegramUserGet, "sendAudio", messageId);
                    historyService.saveHistory(history1);
                }

                @Override
                public void onFailure(SendAudio sendAudio, IOException e) {
                }
            });
        } catch (IOException ignored) {

        }
        return user;
    }

    @Override
    public TelegramUser sendDocument(long chatId, MultipartFile file) {
        String name = file.getOriginalFilename();
        TelegramUser user = new TelegramUser();
        user.setId(chatId);
        try (BufferedOutputStream bufferedOutputStream =
                     new BufferedOutputStream(new FileOutputStream(name))) {
            bufferedOutputStream.write(file.getBytes());

            TelegramBot bot = new TelegramBot(token);
            bot.execute(new SendDocument(chatId, new File(name)), new Callback<SendDocument, SendResponse>() {

                @Override
                public void onResponse(SendDocument sendDocument, SendResponse sendResponse) {
                   // int messageId = sendResponse.message().messageId();

                    //TODO handle messageId
                 //   System.out.println(messageId);

                //    System.out.println(sendResponse);
                    int messageId = sendResponse.message().messageId();
                    TelegramUser telegramUserGet = telegramUserService.get(chatId);
                    History history1 = new History(telegramUserGet, "sendDocument", messageId);
                    historyService.saveHistory(history1);
                }

                @Override
                public void onFailure(SendDocument sendDocument, IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ignored) {
        }
        return user;
    }

}
