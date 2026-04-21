package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Apprentice.class, name = "apprentice"),
        @JsonSubTypes.Type(value = Trainer.class, name = "trainer"),
        @JsonSubTypes.Type(value = Admin.class, name = "admin")
})

public class User {

    private long id;

    private String login;

    private String surname;

    private String name;

    private String patronymic;

    private String password;

    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate regDate = LocalDate.now();

    public User(String surname, String name, String patronymic){
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
}
