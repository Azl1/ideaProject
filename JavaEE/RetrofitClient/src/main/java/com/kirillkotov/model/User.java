package com.kirillkotov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private long id;

    @NonNull
    private String login;

    @NonNull
    private String password;

    @NonNull
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate regDate;
}
