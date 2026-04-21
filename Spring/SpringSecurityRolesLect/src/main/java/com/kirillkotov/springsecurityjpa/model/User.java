package com.kirillkotov.springsecurityjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//TODO создать новый класс Role который имеет id и название
//TODO классу роли будет соответствовать таблица в базе данных roles
//TODO сделать связь между ролями и юзерами как многие ко многим

//TODO сделать для него контроллер который производит
// добавление новой роли в список всех ролей(доступен только администратору)

//TODO изначально юзер при регистрации не приходит с ролями.
// Роли юзеру может назначить только администратор,
// сделать для этого отдельный метод в UserController
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String userName;
    private String password;
    private boolean active = true;

    //TODO тут заменить строку на список ролей этого юзера
    //TODO при аннотации ManyToMany в случае возникновения
    // ошибки LazyInitializationException попробуйте
    // поставить fetchType.Eager
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<Role> roles;

    public void add(Role role){
        this.roles.add(role);
    }
}
