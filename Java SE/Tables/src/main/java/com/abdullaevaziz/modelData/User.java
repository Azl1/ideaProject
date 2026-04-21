package com.abdullaevaziz.modelData;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    /**
     * 1. Создать модель данных User с полями: идентификатор (целое число),
     * имя пользователя, дата регистрации (dd.MM.yyyy HH:mm:ss),
     * электронная почта, возраст, страна, отправлено ли письмо
     * (название поля: isSend - true/false, исключить данное поле из json,
     * как на загрузку, так и на выгрузку)
     */

    private int id;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime registrationDate;
    private String email;
    private int age;
    private String country;
    @JsonIgnore
    private boolean isSend;

    public User() {
    }

    public User(int id, String name, LocalDateTime registrationDate, String email,
                int age, String country, boolean isSend) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.email = email;
        this.age = age;
        this.country = country;
        this.isSend = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && isSend == user.isSend
                && Objects.equals(name, user.name) && Objects.equals(registrationDate,
                user.registrationDate) && Objects.equals(email, user.email) && Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registrationDate, email, age, country, isSend);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", county='" + country + '\'' +
                ", isSend=" + isSend +
                '}';
    }
}
