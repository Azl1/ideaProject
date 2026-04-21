package com.abdullaevaziz.studentsspringbootdataclient.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    @NonNull
    private String userName;
    @NonNull
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
