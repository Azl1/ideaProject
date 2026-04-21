package com.abdullaevaziz.config;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.securety.JwtUserDetailsService;
import com.abdullaevaziz.securety.jwt.JwtConfigurer;
import com.abdullaevaziz.securety.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private ObjectMapper objectMapper;

    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setJwtUserDetailsService(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/authentication").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET,"/user/**").hasAnyRole("ADMIN", "TRAINER", "APPRENTICE")
                .antMatchers(HttpMethod.DELETE,"/user/**").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/admin").permitAll()
                //.antMatchers(HttpMethod.POST, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/apprentice").permitAll()
                .antMatchers(HttpMethod.GET, "/apprentice").hasAnyRole("ADMIN", "TRAINER")
                .antMatchers(HttpMethod.GET,"/apprentice/**").hasAnyRole("ADMIN", "TRAINER", "APPRENTICE")
                .antMatchers(HttpMethod.PUT, "/apprentice").hasAnyRole("ADMIN", "APPRENTICE")
                .antMatchers(HttpMethod.DELETE, "/apprentice/**").hasAnyRole("ADMIN", "APPRENTICE")

                .antMatchers(HttpMethod.POST, "/trainer").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/trainer").hasAnyRole("ADMIN", "APPRENTICE")
                .antMatchers(HttpMethod.GET,"/trainer/**").hasAnyRole("ADMIN", "APPRENTICE", "TRAINER")
                .antMatchers(HttpMethod.PUT, "/trainer").hasAnyRole("ADMIN", "TRAINER")
                .antMatchers(HttpMethod.DELETE, "/trainer/**").hasAnyRole("ADMIN", "TRAINER")

                .antMatchers(HttpMethod.POST, "/trainerSchedule/**").hasAnyRole("ADMIN", "TRAINER")
                .antMatchers(HttpMethod.GET,"/trainerSchedule/**").hasAnyRole("ADMIN", "APPRENTICE", "TRAINER")
                .antMatchers(HttpMethod.DELETE, "/trainerSchedule/**").hasAnyRole("ADMIN", "TRAINER")

                .antMatchers(HttpMethod.POST, "/training").hasAnyRole("ADMIN", "APPRENTICE", "TRAINER")
                .antMatchers(HttpMethod.GET,"/training/**").hasAnyRole("ADMIN", "APPRENTICE", "TRAINER")
                .antMatchers(HttpMethod.DELETE, "/training/**").hasAnyRole("ADMIN", "TRAINER", "APPRENTICE")

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().
                        accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    new ObjectMapper().writeValue(response.getOutputStream(),
                            new ResponseResult<>("Insufficient privileges", null));
                })

                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    String authenticateHeader = response.getHeader("WWW-Authenticate");
                    String text = authenticateHeader != null ? "Incorrect username or password" : "Authentication error";
                    new ObjectMapper().writeValue(response.getOutputStream(), new ResponseResult<>(text, null));
                })
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
