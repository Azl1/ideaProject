package com.abdullaevaziz.controllers;

import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.model.History;
import com.abdullaevaziz.model.TelegramUser;
import com.abdullaevaziz.service.*;
import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.TelegramRequest;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@BotController
public class TelegramBotController  implements TelegramMvcController {

    private TelegramUserService telegramUserService;
    private ComplimentService complimentService;
    private ComplimentsTelegramUsersService complimentsTelegramUsersService;
    private HistoryService historyService;
    private FileService fileService;

    private Keyboard replyKeyboardRegister;
    private Keyboard keyboardNextAllPhotos;


    @Autowired
    public void setTelegramUserService(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
    }

    @Autowired
    public void setComplimentService(ComplimentService complimentService) {
        this.complimentService = complimentService;
    }

    @Autowired
    public void setComplimentsTelegramUsers(ComplimentsTelegramUsersService complimentsTelegramUsersService){
        this.complimentsTelegramUsersService = complimentsTelegramUsersService;
    }

    @Autowired
    public void setHistoryService(HistoryService historyService){
        this.historyService = historyService;
    }

    @Autowired
    public void setFileService(FileService fileService){
        this.fileService = fileService;
    }


    @Value("${bot.token}")
    private String token;



    @PostConstruct
    public void init() {
        this.replyKeyboardRegister = new ReplyKeyboardMarkup(
                "/register")
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

        //TODO тут делаешь еще 2 клаву с опциями как сказано в задании
        this.keyboardNextAllPhotos = new ReplyKeyboardMarkup("/next", "/all", "/photos")
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);
    }

    @Override
    public String getToken() {
        return this.token;
    }

    private SendMessage sendMessageWithButtons(long chatId,
                                               String message,
                                               Keyboard keyboard) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.replyMarkup(keyboard);
        return sendMessage.parseMode(ParseMode.HTML);
    }

    private SendPhoto sendImageWithButtons(long chatId, File file, Keyboard keyboard) {
        SendPhoto sendPhoto = new SendPhoto(chatId, file);
        sendPhoto.replyMarkup(keyboard);
        return sendPhoto.parseMode(ParseMode.HTML);
    }

    /**
     * 1. Пользователь telegram бота входит в систему используя /start.
     * Ваше приложение запоминает в базу данных
     * имя пользователя(telegram_user_name) из телеграмма и
     * его идентификатор чата(chat_id). Учтите тот момент,
     * что у пользователя не всегда отправляется имя из соображений
     * политики конфиденциальности (сохранить тогда как null в базу данных)
     */
    @BotRequest(value = "/start", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest start(User user, Chat chat, TelegramRequest telegramRequest) {
        //TODO это айдишник того сообщения что приходит из бота в программу в данном случае это будет /start
        //TODO ты можешь вставлять этот код туда куда тебе именно нужно
        int messageId = telegramRequest.getMessage().messageId();
        //TODO для юзера когда задаешь хистори еще надо добавлять мессаджайди в каждом действии пользователя


        long chatId = chat.id();
        telegramUserService.add(new TelegramUser(chatId, user.username()));
        TelegramUser telegramUserGet = this.telegramUserService.get(chatId);
        History history = new History(telegramUserGet,"/start", messageId);
        this.historyService.saveHistory(history);
       
        //а это мы будем определять айдишник того сообщения которое мы уже отправляем человеку в ответ
        telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage,
                                   SendResponse sendResponse) {
                int messageId = sendResponse.message().messageId();
                History history1 = new History(telegramUserGet, "start", messageId);
                historyService.saveHistory(history1);
                //TODO handle messageId
                System.out.println(messageId);
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                System.out.println(sendMessage);
                e.printStackTrace();
            }
        });
        
        return sendMessageWithButtons(chat.id(),
                "Добро пожаловать в бот для регистрации нажмите /register ",
                this.replyKeyboardRegister);

    }

    /**
     * telegram_bot_spring_boot
     * 3. Бот предлагает пользователю клавиатуру с кнопкой /register
     * которая позволяет ввести логин(login),
     * затем имя пользователя(name) и затем возраст(age),
     * которые записываются в базу данных
    */
    @BotRequest(value = "/register", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest register(User user, Chat chat, TelegramRequest telegramRequest) {
        int messageId = telegramRequest.getMessage().messageId();
        long getId = chat.id();
        TelegramUser telegramUserGet = this.telegramUserService.get(getId);
        History history = new History(telegramUserGet,"/register", messageId);

        this.historyService.saveHistory(history);
        System.out.println(getId);

        this.telegramUserService.updateStep(chat.id(), "login");

        telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage,
                                   SendResponse sendResponse) {
                int messageId = sendResponse.message().messageId();
                History history1 = new History(telegramUserGet, "login", messageId);
                historyService.saveHistory(history1);
                //TODO handle messageId
                System.out.println(messageId);
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                System.out.println(sendMessage);
                e.printStackTrace();
            }
        });
        return sendMessageWithButtons(chat.id(), "Введите login:",
                this.replyKeyboardRegister);
    }

    @BotRequest(value = "{message:[\\S ]+}", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest allMessage(@BotPathVariable("message") String text,
                                  User user, Chat chat, TelegramRequest telegramRequest) {

        int messageId = telegramRequest.getMessage().messageId();
        long getId = chat.id();
        TelegramUser telegramUserGet = this.telegramUserService.get(getId);

        String step = telegramUserGet.getStep();

        if (step == null) {
            return sendMessageWithButtons(chat.id(), "Ошибка: step is null",
                    this.replyKeyboardRegister);
        }

       else if (step.equals("login")) {
            telegramUserGet.setLogin(text);
            History history = new History(telegramUserGet,"/login", messageId);

            this.historyService.saveHistory(history);
            telegramUserGet.setStep("name");
            this.telegramUserService.add(telegramUserGet);

            this.historyService.saveHistory(history);

            telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
                @Override
                public void onResponse(SendMessage sendMessage,
                                       SendResponse sendResponse) {
                    int messageId = sendResponse.message().messageId();
                    History history1 = new History(telegramUserGet, "name", messageId);
                    historyService.saveHistory(history1);
                    //TODO handle messageId
                    System.out.println(messageId);
                }

                @Override
                public void onFailure(SendMessage sendMessage, IOException e) {
                    System.out.println(sendMessage);
                    e.printStackTrace();
                }
            });

            return sendMessageWithButtons(chat.id(), "Введите ваше имя:",
                    this.replyKeyboardRegister);
        } else if (step.equals("name")) {
            telegramUserGet.setName(text);
            telegramUserGet.setStep("age");

            History history = new History(telegramUserGet,"/age", messageId);
            this.historyService.saveHistory(history);

            this.telegramUserService.add(telegramUserGet);
            telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
                @Override
                public void onResponse(SendMessage sendMessage,
                                       SendResponse sendResponse) {
                    int messageId = sendResponse.message().messageId();
                    History history1 = new History(telegramUserGet, "age", messageId);
                    historyService.saveHistory(history1);
                    //TODO handle messageId
                    System.out.println(messageId);
                }

                @Override
                public void onFailure(SendMessage sendMessage, IOException e) {
                    System.out.println(sendMessage);
                    e.printStackTrace();
                }
            });
            return sendMessageWithButtons(chat.id(), "Введите ваш возраст:",
                    this.replyKeyboardRegister);
        } else if (step.equals("age")) {
            telegramUserGet.setAge(Integer.parseInt(text));
            telegramUserGet.setStep("age");

            History history = new History(telegramUserGet,"/age", messageId);
            this.historyService.saveHistory(history);

            this.telegramUserService.add(telegramUserGet);

            telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
                @Override
                public void onResponse(SendMessage sendMessage,
                                       SendResponse sendResponse) {
                    int messageId = sendResponse.message().messageId();
                    History history1 = new History(telegramUserGet, "age", messageId);
                    historyService.saveHistory(history1);
                    //TODO handle messageId
                    System.out.println(messageId);
                }

                @Override
                public void onFailure(SendMessage sendMessage, IOException e) {
                    System.out.println(sendMessage);
                    e.printStackTrace();
                }
            });
            return sendMessageWithButtons(chat.id(), "Спасибо за регистрацию!:",
                    this.keyboardNextAllPhotos);
        } else if (step.equals("next")) {
            next(user, chat, telegramRequest);
            return sendMessageWithButtons(chat.id(), "Рандомный комплимент: ",
                    this.keyboardNextAllPhotos);
        } else if (step.equals("all")) {
            all(user, chat, telegramRequest);

            return sendMessageWithButtons(chat.id(), "Все комплименты: ",
                    this.keyboardNextAllPhotos);
        } else if (step.equals("photos")){
            File getPhoto = this.fileService.getPhoto(text);
            getAllPhotos(user, chat, telegramRequest);

            return sendImageWithButtons(chat.id(), getPhoto,
                    this.keyboardNextAllPhotos);
        }
        return null;
    }

    private boolean isUserFullyRegistered(TelegramUser user) {
        return user != null &&
                user.getLogin() != null &&
                user.getName() != null &&
                user.getAge() > 0;
    }

    @BotRequest(value = "/next", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest next(User user, Chat chat, TelegramRequest telegramRequest) {
        int messageId = telegramRequest.getMessage().messageId();
        long getId = chat.id();
        TelegramUser telegramUserGet = this.telegramUserService.get(getId);

        if (!isUserFullyRegistered(telegramUserGet)) {
            return sendMessageWithButtons(chat.id(),
                    "Для доступа к этой функции необходимо завершить регистрацию",
                    this.keyboardNextAllPhotos);
        }
        this.telegramUserService.updateStep(chat.id(), "next");
        Compliment complimentGet = this.complimentService.getComplimentRandom(telegramUserGet.getId());
        this.complimentsTelegramUsersService.saveComplimentForUser(telegramUserGet, complimentGet);

        History history = new History(telegramUserGet,"/next", messageId);
        this.historyService.saveHistory(history);
        telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage,
                                   SendResponse sendResponse) {
                int messageId = sendResponse.message().messageId();
                History history1 = new History(telegramUserGet, "next", messageId);
                historyService.saveHistory(history1);
                //TODO handle messageId
                System.out.println(messageId);
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                System.out.println(sendMessage);
                e.printStackTrace();
            }
        });
        return sendMessageWithButtons(chat.id(), complimentGet.getText(),
                this.keyboardNextAllPhotos);
    }

    @BotRequest(value = "/all", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest all(User user, Chat chat, TelegramRequest telegramRequest) {
        long getId = chat.id();
        int messageId = telegramRequest.getMessage().messageId();
        TelegramUser telegramUserGet = this.telegramUserService.get(getId);

        if (!isUserFullyRegistered(telegramUserGet)) {
            return sendMessageWithButtons(chat.id(),
                    "Для доступа к этой функции необходимо завершить регистрацию",
                    this.keyboardNextAllPhotos);
        }
        this.telegramUserService.updateStep(chat.id(), "all");
        List<Compliment> complimentList = this.complimentService.getList();

        History history = new History(telegramUserGet,"/all", messageId);
        this.historyService.saveHistory(history);

        telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage,
                                   SendResponse sendResponse) {
                int messageId = sendResponse.message().messageId();
                History history1 = new History(telegramUserGet, "all", messageId);
                historyService.saveHistory(history1);
                //TODO handle messageId
                System.out.println(messageId);
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                System.out.println(sendMessage);
                e.printStackTrace();
            }
        });

        return sendMessageWithButtons(chat.id(),
                complimentList.stream().map(Compliment::toString).collect(Collectors.joining("\n")),
                this.keyboardNextAllPhotos);
    }

    @BotRequest(value = "/photos", type = {MessageType.CALLBACK_QUERY, MessageType.MESSAGE})
    public BaseRequest getAllPhotos(User user, Chat chat, TelegramRequest telegramRequest) {
        long getId = chat.id();
        int messageId = telegramRequest.getMessage().messageId();
        TelegramUser telegramUserGet = this.telegramUserService.get(getId);
        this.telegramUserService.updateStep(chat.id(), "photos");

        History history = new History(telegramUserGet, "/photos", messageId);
        historyService.saveHistory(history);

        if (!isUserFullyRegistered(telegramUserGet)) {
            return sendMessageWithButtons(chat.id(),
                    "Для доступа к этой функции необходимо завершить регистрацию",
                    this.keyboardNextAllPhotos);
        }

        String getPhotos = this.fileService.getAllPhotos();
        telegramRequest.setCallback(new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage sendMessage,
                                   SendResponse sendResponse) {
                int messageId = sendResponse.message().messageId();
                History history1 = new History(telegramUserGet, "photos", messageId);
                historyService.saveHistory(history1);
                //TODO handle messageId
                System.out.println(messageId);
            }

            @Override
            public void onFailure(SendMessage sendMessage, IOException e) {
                System.out.println(sendMessage);
                e.printStackTrace();
            }
        });
        return sendMessageWithButtons(chat.id(), getPhotos,
                this.keyboardNextAllPhotos);
    }
}
