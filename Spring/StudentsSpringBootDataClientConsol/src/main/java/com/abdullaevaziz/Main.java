package com.abdullaevaziz;

import com.abdullaevaziz.model.Auto;
import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.retrofit.AutoRepository;
import com.abdullaevaziz.retrofit.StudentRepository;
import com.abdullaevaziz.retrofit.UserRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository1 = new UserRepository();
        AutoRepository autoRepository = new AutoRepository();
        StudentRepository studentRepository = new StudentRepository();
        try {

            UserRepository userRepository2 = new UserRepository("1", "1");
            Student student = new Student(1, "Козлов", 7,4,1);
            User userGet1 = userRepository2.get();


            //UserRepository userRepository3 = new UserRepository("2", "2");
            //User userGet2 = userRepository3.get();
            Auto auto = new Auto("Мустанг", 2000, 2023); // без id
            Auto autoAdd = autoRepository.post(auto, 3L);
            //System.out.println(userGet1);
            System.out.println(autoAdd);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}