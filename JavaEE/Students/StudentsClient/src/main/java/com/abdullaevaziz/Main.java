package com.abdullaevaziz;

import com.model.Auto;
import com.model.Student;
import com.repository.AutoRepository;
import com.repository.StudentRepository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();
        Student student1 = new Student(10, "Иванов Иван Иванович", 20, 2, 30000);
        Student student2 = new Student(20, "Петров Петр Петрович", 21, 3, 35000);
        Student student3 = new Student(30, "Сидоров Сидор Сидорович", 22, 4, 37000);
        Student student4 = new Student(40, "Романов Роман Романович", 23, 5, 38000);
        Student student5 = new Student(50, "Степанов Степан Степанович", 24, 1, 40000);
        Student student6 = new Student(55, "Вялый Вял Вялович", 21, 88, 578000);

        AutoRepository autoRepository = new AutoRepository();
        Auto auto1 = new Auto(1, "Ваз", 120, 2008, 1);
        Auto auto2 = new Auto(2, "Таз", 110, 2022, 2);
        Auto auto3 = new Auto(3, "Бмв", 320, 2023, 3);
        Auto auto4 = new Auto(4, "Мерс", 420, 2024, 4);
        Auto auto5 = new Auto(5, "Танк", 220, 2025, 5);


        try {
            Student studentAdd1 = studentRepository.add(student1);
            Student studentAdd2 = studentRepository.add(student2);
            Student studentAdd3 = studentRepository.add(student3);
            Student studentAdd4 = studentRepository.add(student4);
            Student studentAdd5 = studentRepository.add(student5);
            Student studentAdd6 = studentRepository.add(student6);
            System.out.println(studentAdd1);
            System.out.println(studentAdd2);
            System.out.println(studentAdd3);
            System.out.println(studentAdd4);
            System.out.println(studentAdd5);
            System.out.println(studentAdd6);
            Student studentUpdate = studentRepository.update(student5);
            ArrayList<Student> studentArrayList = studentRepository.get();
            System.out.println(studentUpdate);
            System.out.println(studentArrayList);


            Auto autoAdd1 = autoRepository.add(auto1);
            Auto autoAdd2 = autoRepository.add(auto2);
            Auto autoAdd3 = autoRepository.add(auto3);
            Auto autoAdd4 = autoRepository.add(auto4);
            Auto autoAdd5 = autoRepository.add(auto5);
            System.out.println(autoAdd1);
            System.out.println(autoAdd2);
            System.out.println(autoAdd3);
            System.out.println(autoAdd4);
            System.out.println(autoAdd5);
            Auto autoUpdate = autoRepository.update(auto5);
            ArrayList<Auto> autoArrayList = autoRepository.get(auto1);
            System.out.println(autoUpdate);
            System.out.println(autoArrayList);



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}