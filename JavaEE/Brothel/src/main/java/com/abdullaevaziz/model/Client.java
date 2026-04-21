package com.abdullaevaziz.model;

import java.util.Objects;

public class Client {
    private int id;
    private String fio;
    private int numberTel;
    private int age;
    private String preferences;

    public Client() {
    }

    public Client(String fio, int numberTel, int age, String preferences) {
        this.fio = fio;
        this.numberTel = numberTel;
        this.age = age;
        this.preferences = preferences;
    }

    public Client(int id, String fio, int numberTel, int age, String preferences) {
        this.id = id;
        this.fio = fio;
        this.numberTel = numberTel;
        this.age = age;
        this.preferences = preferences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getNumberTel() {
        return numberTel;
    }

    public void setNumberTel(int numberTel) {
        this.numberTel = numberTel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && numberTel == client.numberTel && age == client.age && Objects.equals(fio, client.fio) && Objects.equals(preferences, client.preferences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, numberTel, age, preferences);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", numberTel=" + numberTel +
                ", age=" + age +
                ", preferences='" + preferences + '\'' +
                '}';
    }
}
