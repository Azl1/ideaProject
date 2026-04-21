package com.kirillkotov.javafxlect.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {
    private long id;
    private String fio;
    private int age;
    private Address address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDay;

    public User() {
    }

    public User(String fio, int age, Address address, LocalDate birthDay) {
        this.fio = fio;
        this.age = age;
        this.address = address;
        this.birthDay = birthDay;
    }

    public User(long id, String fio, int age, Address address, LocalDate birthDay) {
        this.id = id;
        this.fio = fio;
        this.age = age;
        this.address = address;
        this.birthDay = birthDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", birthDay=" + dateTimeFormatter.format(birthDay) +
                '}';
    }
}
