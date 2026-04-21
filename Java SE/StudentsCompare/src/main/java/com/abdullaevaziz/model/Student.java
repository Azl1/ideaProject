package com.abdullaevaziz.model;

import java.util.Objects;

/**
 * Класс студент с полями: имя, фамилия, рейтинг, курс
 */
public class Student implements Comparable<Student>{

    private String name;
    private String family;
    private int rating;
    private int kurs;


    public Student() {
    }

    public Student(String name, String family, int rating, int kurs) {
        this.name = name;
        this.family = family;
        this.rating = rating;
        this.kurs = kurs;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getKurs() {
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rating == student.rating && kurs == student.kurs && Objects.equals(name, student.name) && Objects.equals(family, student.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, rating, kurs);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", rating=" + rating +
                ", kurs=" + kurs +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.getKurs(), o.getKurs());
    }


}
