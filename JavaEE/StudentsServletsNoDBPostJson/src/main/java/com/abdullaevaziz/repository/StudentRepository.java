package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Student;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Student> studentList = new ArrayList<>();

    /**
     * 2. Конструктор без аргументов репозитория производит загрузку данных
     * в список из json файла (имена файлов вынести в класс Constants),
     * если файла нет, то оставить список пустым.
     */
    public StudentRepository(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.STUDENT_FILE))) {
            this.studentList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        }catch (IOException ignored) {
        }
    }

    /**
     * Метод добавления объекта в репозиторий производит добавление данных в список,
     * присваивает переданному объекту id(берется максимальный id в коллекции и прибавляется 1)
     * и производит выгрузку измененной коллекции в файл.
     */
    public boolean addStudent(Student student) {
        student.setId(this.studentList.stream().mapToInt(Student::getId).max().orElse(0) + 1);
        this.studentList.add(student);
        saveStudent();
        return true;
    }

    private void saveStudent() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.STUDENT_FILE))) {
            this.objectMapper.writeValue(bufferedWriter, this.studentList);
        } catch (IOException io) {
        }
    }

    /**
     * Метод удаления производит удаление объекта по id и производит выгрузку измененной коллекции в файл.
     */
    public boolean removeStudent(int id) {
        boolean b = this.studentList.removeIf(student -> student.getId() == id);
        saveStudent();
        return b;
    }

    /**
     * Метод получения всех объектов должен вернуть коллекцию из репозитория
     */
    public List<Student> getAllStudents() {
        return this.studentList;
    }

    /**
     * Метод получения объекта по id осуществляет получение объекта
     * из коллекции по его id, либо null, если такого объекта нет.
     */
    public Student get(int id) {
        return studentList.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

    /**
     * Метод обновления принимает на вход новый объект.
     * Получает старый объект из коллекции по id нового объекта.
     * Если такого объекта нет в коллекции, то возвращает false,
     * если объект найден, то производит его замену на новый в списке.
     * Производит выгрузку измененной коллекции в файл, возвращая true
     */
    public boolean updateStudent(Student newStudent) {
        for (int i = 0; i < this.studentList.size(); i++) {
            if (this.studentList.get(i).getId() == newStudent.getId()) {
                this.studentList.set(i, newStudent);
                saveStudent();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRepository that = (StudentRepository) o;
        return Objects.equals(studentList, that.studentList) && Objects.equals(objectMapper, that.objectMapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentList, objectMapper);
    }

    @Override
    public String toString() {
        return "StudentRepository{" +
                "studentList=" + studentList +
                '}';
    }

}
