package com.abdullaevaziz;

import com.abdullaevaziz.repository.AdminRepository;
import com.abdullaevaziz.repository.TelegramUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AdminRepository.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}