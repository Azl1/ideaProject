package com.kirillkotov.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String userName;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})},
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
