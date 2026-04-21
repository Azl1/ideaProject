package com.abdullaevaziz.model;

import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private String family;
    private int age;
    private int numberPhone;
    private String mail;

    public Person() {
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Person(int id, String name, String family, int age, int numberPhone, String mail) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.age = age;
        this.numberPhone = numberPhone;
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && numberPhone == person.numberPhone && Objects.equals(name, person.name) && Objects.equals(family, person.family) && Objects.equals(mail, person.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, family, age, numberPhone, mail);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", numberPhone=" + numberPhone +
                ", mail='" + mail + '\'' +
                '}';
    }
}
