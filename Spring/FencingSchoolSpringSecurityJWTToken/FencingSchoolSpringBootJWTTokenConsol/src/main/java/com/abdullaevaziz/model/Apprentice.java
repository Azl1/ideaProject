package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Apprentice extends User{

    private String phoneNumber;

    public Apprentice(
            String login, String surname, String name, String patronymic, String password,  String phoneNumber) {
        super();
        this.setLogin(login);
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setPassword(password);
        this.phoneNumber = phoneNumber;
    }
}
