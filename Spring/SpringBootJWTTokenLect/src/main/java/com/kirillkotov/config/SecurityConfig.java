package com.kirillkotov.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirillkotov.dto.ResponseResult;
import com.kirillkotov.security.jwt.JwtConfigurer;
import com.kirillkotov.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private ObjectMapper objectMapper;


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .antMatchers("/role").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    request.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    objectMapper.writeValue(response.getWriter(),
                            new ResponseResult<>("No authentication", null));
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    request.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    objectMapper.writeValue(response.getWriter(),
                            new ResponseResult<>("Forbidden", null));
                })
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
