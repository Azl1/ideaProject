package com.abdullaevaziz.security;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/student").permitAll()
                .antMatchers(HttpMethod.GET, "/student/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auto/**").permitAll()
                .antMatchers(HttpMethod.GET, "/auto/**").permitAll()
                .anyRequest().authenticated()

                //TODO в случае возникновения ошибки 401 в тестах сделать
                // возврат ResponseResult с сообщением если не посылать
                // вообще логина и пароля, то Ошибка аутентификации,
                // а если неверный логин или пароль, то Неверный логин или пароль
                .and().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    String authenticateHeader = response.getHeader("WWW-Authenticate");
                    String text = authenticateHeader != null ? "Incorrect login or password" : "Authentication error";
                    new ObjectMapper().writeValue(response.getOutputStream(), new ResponseResult<>(text, null));
                })
                .and()
                .httpBasic();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

