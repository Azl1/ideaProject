package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Repository repository = new Repository("students.csv");
            System.out.println(repository);

            /*Student student1 = new Student("Иванов", "Иван", 5);
            Student student2 = new Student("Петров", "Петя", 4);
            Student student3 = new Student("Алексеев", "Леха", 3);
            Student student4 = new Student("Романович", "Рома", 2);
            Student student5 = new Student("Сидоров", "Седой", 5);

            student1.addRating(5);
            student1.addRating(5);
            student1.addRating(2);
            student1.addRating(3);
            student1.addRating(4);*/

            repository.save("out.txt");
            //System.out.println(student1.toCSV());
            System.out.println();
            ArrayList<Student> res1 = repository.getExcellentStudents();
            System.out.println(res1);
            System.out.println();

            ArrayList<Double> res2 = repository.getAverageRating();
            System.out.println(res2);
            System.out.println();

            ArrayList<Student> res3 = repository.getAverageRatingsStudents();
            System.out.println(res3);
            System.out.println();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}