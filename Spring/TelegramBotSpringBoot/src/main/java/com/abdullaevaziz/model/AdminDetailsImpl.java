package com.abdullaevaziz.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class AdminDetailsImpl implements UserDetails {

    private long id;
    private String login;
    private String password;
    //private String role;

    public AdminDetailsImpl(Admin admin) {
        this.id = admin.getId();
        this.login = admin.getUserName();
        this.password = admin.getPassword();
        //this.role = telegramUser.getClass().getSimpleName().toUpperCase();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }
}

