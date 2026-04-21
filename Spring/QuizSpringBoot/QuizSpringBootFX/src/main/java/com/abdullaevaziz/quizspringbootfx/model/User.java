package com.abdullaevaziz.quizspringbootfx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Long id;

    @NonNull
    private String login;
    @NonNull
    private String name;
    @NonNull
    private String password;

    private UserType userType;

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
