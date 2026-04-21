package com.kirillkotov.springsecurityjpa.model;

//TODO создать новый класс Role который имеет id и название
//TODO классу роли будет соответствовать таблица в базе данных roles
//TODO сделать связь между ролями и юзерами как многие ко многим

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}
