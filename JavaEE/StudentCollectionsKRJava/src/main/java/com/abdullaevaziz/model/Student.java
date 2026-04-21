package com.abdullaevaziz.model;

import java.util.Objects;

public class Student implements Comparable<Student>{

    private String name;
    private String family;
    private int course;

    public Student(String name, String family, int course) {
        this.name = name;
        this.family = family;
        this.course = course;
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

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course && Objects.equals(name, student.name) && Objects.equals(family, student.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", course=" + course +
                '}';
    }

    public String toCSV(){
        return this.name + ";" + this.family + ";" + this.course;
    }

    /**
     * 4. Отсортировать коллекцию студентов-заочников
     * в лексикографическом порядке по фамилии и имени
     */
    @Override
    public int compareTo(Student o) {
        if (this.name.equals(o.name)){
            return this.family.compareTo(o.getFamily());
        }
        return this.name.compareTo(o.getName());
    }


}
