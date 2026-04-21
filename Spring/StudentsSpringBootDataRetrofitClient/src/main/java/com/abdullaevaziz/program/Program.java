package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.retrofit.StudentRepository;

import java.util.List;

public class Program {
    public static void main(String[] args) {

/*        StudentRepository studentRepository = new StudentRepository();

        try {
            //Add data to server
            StudentRepository postedStudent = studentRepository.post(new Student("Лермонтов", 25, 22, 5000));
            System.out.println("Student is added to server!");
            System.out.println(postedStudent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Get All Student from Server
            List<Student> all = studentRepository.getAll();
            System.out.println("\nAll student from server:\n" + all);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Get Student by id from Server
            Student student = studentRepository.get(1L);
            System.out.println("\nStudent by id 1 from server:\n" + student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Get Student by id from Server - incorrect data
            System.out.println("\nStudent by id 100 does not exists in server:\n");
            Student studentIncorrect = studentRepository.get(100L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Update data to server
            Student putStudent = studentRepository.put(new Student(1, "1", "1", "1", null));
            System.out.println("Student is updated to server!");
            System.out.println(putStudent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Delete Student by id from Server
            Student deleted = studentRepository.delete(1L);
            System.out.println("\nUser by id 1 deleted from server:\n" + deleted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //Delete Student by id from Server - incorrect data
            System.out.println("\nUser by id 100 does not exists in server:\n");
            Student deletedIncorrect = studentRepository.get(100L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Student postedStudent1 = studentRepository.post(new Student("T1", "123", "alex"));
            Student postedStudent2 = studentRepository.post(new Student("T2", "123", "alex"));

            //Delete Users by name from Server
            List<Student> deleted = studentRepository.delete("alex");
            System.out.println(deleted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }
}