package com.kirillkotov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "Enter login") //@NotBlank: Ensures that the field is not null, empty, or only whitespace.
    private String login;

    @NonNull
    @NotBlank(message = "Enter first name") //@NotBlank: Ensures that the field is not null, empty, or only whitespace.
    private String firstName;

    @NonNull
    @NotBlank(message = "Enter last name") //@NotBlank: Ensures that the field is not null, empty, or only whitespace.
    private String lastName;

    @NonNull
    @Past(message = "Birthday couldn't be in future date") //@Past: Ensures the field represents a date/time in the past.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    @Email(message = "Unacceptable email format") //@Email: Validates that the field is a properly formatted email. If null throws DataIntegrityViolationException
    @NotNull(message = "Enter email") //@NotNull to intercept exception from @Email
    private String email;

    @Pattern(regexp = "^[0-9]{6}$", message = "Postal code must be 6-digit number.") //@Pattern: Validates the field against a specified regular expression. If null throws DataIntegrityViolationException
    @NotNull(message = "Enter postal code") //@NotNull to intercept exception from @Pattern
    private String postalCode;

    @javax.validation.constraints.Size(min = 5, max = 50, message = "Address should have a length between 5 and 50 characters") //@Size: Specifies minimum and maximum allowable lengths for strings, arrays, collections, or maps.
    @NotNull(message = "Enter address") //@NotNull to check field. @Size doesn't check on null.
    private String address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private List<TV> tvs;
}
