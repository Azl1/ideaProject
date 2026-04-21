package com.abdullaevaziz.chatspringbootfx.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private long id;

    @NonNull
    private String login;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @Override
    public String toString() {
        return
                " User name='" + name + '\'';
    }
}
