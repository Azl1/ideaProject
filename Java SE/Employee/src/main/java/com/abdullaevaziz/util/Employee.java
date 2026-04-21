package com.abdullaevaziz.util;

import java.util.Objects;

public class Employee implements Comparable<Employee> { //TODO тут имплементровать компарабле от эмплое и метод компарету где делаешь сравнение

    private String family;
    private String name;
    private int number;
    private int rating;

    public Employee() {
    }

    public Employee(String family, String name, int number, int rating) {
        this.family = family;
        this.name = name;
        this.number = number;
        this.rating = rating;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return number == employee.number && rating == employee.rating && Objects.equals(family, employee.family) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(family, name, number, rating);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "family='" + family + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", rating=" + rating +
                '}';
    }

    public String CSV(){
        return this.family + ";" + this.name + ";" + this.number + ";" + this.rating;
    }

    @Override
    public int compareTo(Employee o) {
        if(this.family.equals(o.family)){
            return this.name.compareTo(o.name);
        }
        return this.family.compareTo(o.family);
    }
}
