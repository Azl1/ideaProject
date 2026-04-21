package com.kirillkotov.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kirillkotov.model.Status;
import com.kirillkotov.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
public class JwtUser implements UserDetails {
    @JsonIgnore
    private final long id;
    private final String username;
    private final String password;
    private final String email;
    private final boolean enabled;
    @JsonIgnore
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(User user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream()
                .map(x->new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());;
        this.enabled = user.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public long getId() {
        return id;
    }
}
