package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Objects;

public class Student {
    private String family;
    private String name;
    private int kurs;
    private ArrayList<Integer> arrayListRatings = new ArrayList<>();

    public Student() {
    }

    public Student(String family, String name, int kurs) {
        this.family = family;
        this.name = name;
        this.kurs = kurs;
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

    public int getKurs() {
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    public void addRating(int value) {
        this.arrayListRatings.add(value);
    }

    /**
     * Студенты со средним рейтингом.
     */
    public double averageRatings() {
        double sum = 0;
        for (int i = 0; i < this.arrayListRatings.size(); i++) {
            sum += arrayListRatings.get(i);
        }
        return sum / arrayListRatings.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return kurs == student.kurs && Objects.equals(family, student.family) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(family, name, kurs);
    }

    @Override
    public String toString() {
        return "Student{" +
                "family='" + family + '\'' +
                ", name='" + name + '\'' +
                ", kurs=" + kurs +
                ", arrayListRatings=" + arrayListRatings +
                '}';
    }

    public String toCSV() {
        return this.family + ";" + this.name + ";" + this.kurs + ";"
                + this.arrayListRatings + ";";
    }

    /**
     * Возвращает true если студент отличник true, если нет то false.
     */
    public boolean isExcellent() {
        for (Integer rating : this.arrayListRatings) {
            if (rating != 5) {
                return false;
            }
        }
        return true;
    }

}
