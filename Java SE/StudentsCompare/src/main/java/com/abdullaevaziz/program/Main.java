package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Student;

import java.util.Comparator;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name1 = scanner.next();
        String fio1 = scanner.next();
        int reyting1 = scanner.nextInt();
        int kurs1 = scanner.nextInt();

        String name2 = scanner.next();
        String fio2 = scanner.next();
        int reyting2 = scanner.nextInt();
        int kurs2 = scanner.nextInt();

        Student student1 = new Student(name1, fio1, reyting1, kurs1);
        Student student2 = new Student(name2, fio2, reyting2, kurs2);
        System.out.println();
        if (student1.compareTo(student2) < 0) {
            System.out.println("student1 < student2");
        } else if (student1.compareTo(student2) > 0){
            System.out.println("student1 > student2");
        } else {
            System.out.println("student1 = student2");
        }

        Comparator<Student> comparator1 = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getKurs() == o2.getKurs()){
                    return Integer.compare(o1.getRating(), o2.getRating());
                }
                return Integer.compare(o1.getKurs(), o2.getKurs());
            }
        };
        System.out.println();
        if (comparator1.compare(student1, student2) < 0) {
            System.out.println("Рейтинг student1 < Рейтинг student2");
        } else if (student1.compareTo(student2) > 0){
            System.out.println("Рейтинг student1 > Рейтинг student2");
        } else {
            System.out.println("Рейтинг student1 = Рейтинг student2");
        }


        Comparator<Student> comparator2 = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getKurs(), o2.getKurs());
            }
        };
        System.out.println();
        if (comparator2.compare(student1, student2) < 0) {
            System.out.println("Курс student1 < Курс student2");
        } else if (student1.compareTo(student2) > 0){
            System.out.println("Курс student1 > Курс student2");
        } else {
            System.out.println("Курс student1 = Курс student2");
        }


        Comparator<Student> comparator3 = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getFamily().compareTo(o2.getFamily());
            }
        };
        System.out.println();
        if (comparator3.compare(student1, student2) < 0) {
            System.out.println("Фамилия student1 < Фамилия student2");
        } else if (student1.compareTo(student2) > 0){
            System.out.println("Фамилия student1 > Фамилия student2");
        } else {
            System.out.println("Фамилия student1 = Фамилия student2");
        }

    }
}