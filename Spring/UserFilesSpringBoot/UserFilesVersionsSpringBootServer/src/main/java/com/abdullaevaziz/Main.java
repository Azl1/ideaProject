package com.abdullaevaziz;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Main {

        public static void main(String[] args) {
            SpringApplication.run(Main.class, args);
            User user = new User();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.matches("222", user.getPassword()));
        }


}