package com.abdullaevaziz.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "telegram_users")
public class TelegramUser {
    @Id
    @NonNull
    private long id;

    @NonNull
    @Column(name = "telegram_user_name")
    private String telegramUserName;

    private String login;
    private String password;
    private String name;

    private int age;

    private String step;

}
