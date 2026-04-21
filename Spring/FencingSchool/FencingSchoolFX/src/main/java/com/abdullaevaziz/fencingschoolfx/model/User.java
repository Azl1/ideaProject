package com.abdullaevaziz.fencingschoolfx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
/**
 * 1. UserRepository1 – пользователь системы
 */
public class User {

    private long id;

    @NonNull
    @ToString.Exclude
    private String login;

    @NonNull
    @ToString.Exclude
    private String password;

    @NonNull
    @ToString.Exclude
    private String name;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime regDate = LocalDateTime.now();
}
