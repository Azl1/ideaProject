package com.abdullaevaziz;


import com.abdullaevaziz.retrofit.UserRepository;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        //TODO через ретрофит обратиться к серверу
        // послать логин и пароль получить токен токен вывести в консоль

        UserRepository userRepository = new UserRepository();
        try {
            String token = userRepository.authenticate("222", "222");
            System.out.println("Token: " + token + "\n");


            //TODO взят перемнную token распарсить ее и вывести на экран логин айди и роль
            //TODO парситьь так же как я это делал в лекции в классе токенпровайдер

            String secretKey = "jwtappsecret";
            
            String res1 = (String) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("username");
            String res2 = (String) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("role");
            Integer res3 = (Integer) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("id");
            System.out.println("Логин: " + res1);
            System.out.println("Роль: " + res2);
            System.out.println("Id: " + res3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}