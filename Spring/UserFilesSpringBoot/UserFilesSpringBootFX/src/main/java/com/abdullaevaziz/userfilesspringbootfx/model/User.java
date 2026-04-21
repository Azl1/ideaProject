package com.abdullaevaziz.userfilesspringbootfx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class User {

    private long id;

    private String login;

    private String password;

    private String fio;


    public User(String login, String password, String fio) {
        this.login = login;
        this.password = password;
        this.fio = fio;
    }
}
