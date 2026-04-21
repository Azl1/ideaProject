package com.abdullaevaziz.model;

import java.util.Objects;

import static java.lang.CharSequence.compare;

public class Student implements Comparable<Student>{

    private String name;
    private String family;
    private int courses;
    public Student() {

    }
    public Student(String name, String family, int courses) {
        this.name = name;
        this.family = family;
        this.courses = courses;
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

    public int getCourses() {
        return courses;
    }

    public void setCourses(int courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return courses == student.courses && Objects.equals(name, student.name) && Objects.equals(family, student.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, courses);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", courses=" + courses +
                '}';
    }

    public String toCSV() {
        return this.name + ";" + this.family + ";" + this.courses;
    }

    /**
     * Отсортировать коллекцию студентов в лексикографическом
     * порядке по имени и по фамилии
     */
    @Override
    public int compareTo(Student o) {
        if (this.name.equals(o.name)){
            return this.family.compareTo(o.getFamily());
        }
        return this.name.compareTo(o.getName());
    }


}
