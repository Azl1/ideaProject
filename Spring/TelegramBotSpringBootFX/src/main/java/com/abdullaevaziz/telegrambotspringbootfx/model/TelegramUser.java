package com.abdullaevaziz.telegrambotspringbootfx.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class TelegramUser {

    private long id;

    private String telegramUserName;

    private String login;
    private String password;
    private String name;

    private int age;

    private String step;


}
