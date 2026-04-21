package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Repository implements Comparable<Student>{

    private ArrayList<Student> studentArrayList = new ArrayList<>();

    public Repository() {
    }

    public Repository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            try {
                String[] split = line.split(";");
                Student student = new Student(split[0], split[1], Integer.parseInt(split[2]));
                for (int i = 3; i < split.length; i++) {
                    student.addRating(Integer.parseInt(split[i]));
                }
                this.studentArrayList.add(student);
            } catch (RuntimeException ignored) {
            }
        }
    }


    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
            bufferedWriter.write("Family;name;kurs\n");
            for (Student student1 : this.studentArrayList) {
                bufferedWriter.write(student1.toCSV());
                bufferedWriter.newLine();
            }
        }

    }

    @Override
    public String toString() {
        return "Repository{" +
                "studentArrayList=" + studentArrayList +
                '}';
    }

    /**
     * 4.Найти всех студентов отличников, то есть студентов, у которых все оценки отличные
     */
    public ArrayList<Student> getExcellentStudents() {
        ArrayList<Student> res = new ArrayList<>();
        for (Student student : this.studentArrayList){
            if (student.isExcellent()){
                res.add(student);
            }
        }
        return res;
    }

    /**
     * 5.Найти средний рейтинг каждого студента
     */
    public ArrayList<Double> getAverageRating() {
        ArrayList<Double> res = new ArrayList<>();
        double val;
        for (Student student : this.studentArrayList){
            val = student.averageRatings();
            res.add(val);
        }
        return res;
    }

    /**
     * 6.Отсортировать студентов по среднему рейтингу
     */
    @Override
    public int compareTo(Student o) {
        return Double.compare( o.averageRatings(), o.averageRatings());
    }


    /**
     * 7.Найти всех студентов с наибольшим средним баллом, который может быть не обязательно 5, не используя сортировку
     */
    public ArrayList<Student> getAverageRatingsStudents() {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < this.studentArrayList.size(); i++) {
            Student students = this.studentArrayList.get(i);
            if(students.averageRatings() > max){
                max = students.averageRatings();
            }
        }

        ArrayList<Student> res = new ArrayList<>();
        for (Student student : this.studentArrayList){
            if (student.averageRatings() == max){
                res.add(student);
            }
        }
        return res;
    }

}
