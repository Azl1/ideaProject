package com.abdullaevaziz.program;

import com.abdullaevaziz.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        /**
         * 3.Для теста взять данные с ресурса https://jsonplaceholder.typicode.com/users
         */
        try {
            UserRepository userRepository1 = new UserRepository("https://jsonplaceholder.typicode.com/users");
            System.out.println(userRepository1);

            ArrayList<ArrayList<Integer>> res1 = userRepository1.search("name");
            System.out.println(res1);

            HashMap<Character, Integer> res2 = userRepository1.searchChar();
            System.out.println(res2);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}