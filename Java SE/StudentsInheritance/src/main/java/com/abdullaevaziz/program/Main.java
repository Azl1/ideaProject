package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Correspondence;
import com.abdullaevaziz.model.FullTime;
import com.abdullaevaziz.model.Repository;
import com.abdullaevaziz.model.Student;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        try {
            Repository repository = new Repository("students.csv");
            System.out.println(repository);

            /*Student student = new Correspondence("Иван", "Иванов", 1, "ул. Пионерская, д.16");
            Student student1 = new Correspondence("Петр", "Петров", 2, "ул. Пионерская, д.20");
            Student student2 = new Correspondence("Сидор", "Сидоров", 3, "ул. Пионерская, д.30");*/

            Student[] student = new Student[]{
                new FullTime("Роман", "Березин", 4),
                new FullTime("Николай", "Пукин", 5),
                new FullTime("Борис", "Козлов", 5),
                new FullTime("Коля", "Березин", 5)
            };

            /*student3.add(5);
            student4.add(4);
            student5.add(3);
            student6.add(5);*/

           /* repository.add(student);
            repository.add(student1);
            repository.add(student2);
            repository.add(student3);
            repository.add(student4);
            repository.add(student5);
            repository.add(student6);*/

            ArrayList<Student> res = repository.excellentStudents();
            System.out.println(res);
            repository.save("out.txt");

            repository.sort(null);
            System.out.println(repository);
            System.out.println();

            /**
             * Отсортировать коллекцию студентов по курсу
             */
            Comparator<Student> studentComparator = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Integer.compare(o1.getCourses(), o2.getCourses());
                }
            };

            repository.sort(studentComparator);
            System.out.println(repository);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}