package com.abdullaevaziz.fencingschoolspringsecurityfx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User{

    private String email;

    private double salary;

    public Admin(
            String login, String surname, String name, String patronymic, String password, String email, double salary) {
        super();
        this.setLogin(login);
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setPassword(password);
        this.email = email;
        this.salary = salary;
    }
}

