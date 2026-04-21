package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.DistanceLearning;
import com.abdullaevaziz.model.FullTimeLearning;
import com.abdullaevaziz.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Repository {

    private Map<Integer, ArrayList<FullTimeLearning>> map = new HashMap<>();
    private ArrayList<DistanceLearning> studentArrayList = new ArrayList<>();

    public Repository(String fileName) throws FileNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(";");
                if (split[0].equals("FullTime")) {
                    FullTimeLearning fullTimeLearning = new FullTimeLearning(split[1],
                            split[2], Integer.parseInt(split[3]));

                    int courseKey = fullTimeLearning.getCourse();
                    ArrayList<FullTimeLearning> fullTimeLearningArrayList = map.getOrDefault(courseKey,new ArrayList<>());
                    fullTimeLearningArrayList.add(fullTimeLearning);
                    this.map.put(courseKey, fullTimeLearningArrayList);

                    for (int i = 4; i < split.length; i++) {
                        fullTimeLearning.add(Integer.parseInt(split[i]));
                    }
                } else {
                    DistanceLearning distanceLearning = new DistanceLearning(split[1], split[2], Integer.parseInt(split[3]));
                    this.studentArrayList.add(distanceLearning);
                }
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("Name;Family;Courses\n");
            for (Student student : studentArrayList) {
                bufferedWriter.write(student.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public String toString() {
        return "Repository{" +
                "map=" + map +
                ", studentArrayList=" + studentArrayList +
                '}';
    }

    public void add(DistanceLearning dStudent) {
        this.studentArrayList.add(dStudent);
    }

    /**
     * 3. Вернуть студентов, которые являются отличниками
     */
    public ArrayList<Student> excellentStudents() {
        ArrayList<Student> resList = new ArrayList<>();
        for (var entry : this.map.entrySet()) {
            for (Student student : entry.getValue()) {
               if (student instanceof FullTimeLearning){
                 if(((FullTimeLearning) student).isExcellent()){
                     resList.add(student);
                 }
               }
            }
        }
        return resList;
    }

    public void sort(Comparator<Student> comparator) {
        this.studentArrayList.sort(comparator);
    }
}
