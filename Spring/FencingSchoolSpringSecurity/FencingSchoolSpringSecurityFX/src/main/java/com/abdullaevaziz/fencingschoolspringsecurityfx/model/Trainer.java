package com.abdullaevaziz.fencingschoolspringsecurityfx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class Trainer extends User{

    private String type = "trainer";

    private int experience;

    private String email;

    public Trainer(
            String login, String surname, String name, String patronymic, String password,  int experience, String email) {
        super();
        this.setLogin(login);
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setPassword(password);
        this.experience = experience;
        this.email = email;
    }
}
