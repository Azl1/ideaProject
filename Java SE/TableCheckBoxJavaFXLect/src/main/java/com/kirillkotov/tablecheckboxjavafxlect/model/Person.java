package com.kirillkotov.tablecheckboxjavafxlect.model;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private boolean vegetarian;

    public Person() {
    }

    public Person(String firstName, String lastName, boolean vegetarian) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vegetarian = vegetarian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return vegetarian == person.vegetarian && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, vegetarian);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", vegetarian=" + vegetarian +
                '}';
    }
}

