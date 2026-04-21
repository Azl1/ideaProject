package com.abdullaevaziz;

import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

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