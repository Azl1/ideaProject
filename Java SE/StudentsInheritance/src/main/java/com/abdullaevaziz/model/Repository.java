package com.abdullaevaziz.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Репозиторий
 */
public class Repository {

   private ArrayList<Student> studentArrayList = new ArrayList<>();
    /**
     * Разработать схему данных и коллекций, произвести загрузку данных студентов в коллекцию,
     * используя наследование и полиморфизм
     */
    public Repository(String fileName) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            try {
                String[] split = line.split(";");
                if( split[0].equals("FullTime")){
                    FullTime fullTime = new FullTime(split[1], split[2], Integer.parseInt(split[3]));
                    this.studentArrayList.add(fullTime);
                    for (int i = 4; i < split.length; i++) {
                        fullTime.add(Integer.parseInt(split[i]));
                    }
                }
                else {
                    Correspondence correspondence = new Correspondence(split[1], split[2], Integer.parseInt(split[3]), split[4]);
                    this.studentArrayList.add(correspondence);
                }
            } catch (RuntimeException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    /**
     * Выполнить обработку всех ошибок, связанных с загрузкой данных
     */
    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
            bufferedWriter.write("Name;Family;Courses\n");
            for (Student student : this.studentArrayList ) {
                bufferedWriter.write(student.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public String toString() {
        return "Repository{" +
                "studentsList=" + studentArrayList +
                '}';
    }

    public void add(Student student){
        this.studentArrayList.add(student);
    }

    /**
     * Вернуть студентов, которые являются отличниками
     */
    public ArrayList<Student> excellentStudents(){
        ArrayList<Student> res = new ArrayList<>();
        for (Student student : this.studentArrayList) {
            if (student instanceof FullTime){
                FullTime fullTime = (FullTime) student;
                if (fullTime.isExcellent()){
                    res.add(fullTime);
                }
            }
        }
        return res;
    }

    public void sort(Comparator<Student> comparator){
        this.studentArrayList.sort(comparator);
    }
}
