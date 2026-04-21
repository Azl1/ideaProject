package com.kirillkotov.program;

import com.kirillkotov.retrofit.UserRepository;
import com.kirillkotov.model.User;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();

        try {
            //Add data to server
            User postedUser = userRepository.post(new User("Pavel", "123", "Pasha"));
            System.out.println("User is added to server!");
            System.out.println(postedUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Get All Users from Server
            List<User> all = userRepository.getAll();
            System.out.println("\nAll users from server:\n" + all);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Get User by id from Server
            User user = userRepository.get(1L);
            System.out.println("\nUser by id 1 from server:\n" + user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Get User by id from Server - incorrect data
            System.out.println("\nUser by id 100 does not exists in server:\n");
            User userIncorrect = userRepository.get(100L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Update data to server
            User putUser = userRepository.put(new User(1, "1", "1", "1", null));
            System.out.println("User is updated to server!");
            System.out.println(putUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Delete User by id from Server
            User deleted = userRepository.delete(1L);
            System.out.println("\nUser by id 1 deleted from server:\n" + deleted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Delete User by id from Server - incorrect data
            System.out.println("\nUser by id 100 does not exists in server:\n");
            User deletedIncorrect = userRepository.get(100L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            User postedUser1 = userRepository.post(new User("T1", "123", "alex"));
            User postedUser2 = userRepository.post(new User("T2", "123", "alex"));

            //Delete Users by name from Server
            List<User> deleted = userRepository.delete("alex");
            System.out.println(deleted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
