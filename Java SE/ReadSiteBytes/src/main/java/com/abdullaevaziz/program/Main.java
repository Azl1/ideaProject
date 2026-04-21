package com.abdullaevaziz.program;

import com.abdullaevaziz.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            UserRepository userRepository = new UserRepository("https://jsonplaceholder.typicode.com/users");
            System.out.println(userRepository);

            ArrayList<Integer> res = userRepository.search("street");
            System.out.println(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}