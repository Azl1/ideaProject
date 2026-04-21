package com.abdullaevaziz.fileuploaderspringbootfx.model;

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

    private UserType userType;

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
