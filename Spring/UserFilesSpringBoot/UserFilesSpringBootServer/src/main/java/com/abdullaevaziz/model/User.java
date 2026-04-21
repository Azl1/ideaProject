package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Логин обязательный")
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Column(nullable = false)
    private String password;

    private String fio;

}
