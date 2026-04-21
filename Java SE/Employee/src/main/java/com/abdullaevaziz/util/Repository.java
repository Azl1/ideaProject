package com.abdullaevaziz.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Repository {

    private Map<Integer, ArrayList<Employee>> integerEmployeeMap = new HashMap<>();

    public Repository() {
    }

    /**
     * load
     * Произвести сохранение объектов из .csv файла в соответствующую коллекцию (структуру) данных,
     * удовлетворяющую решениям нижестоящих задач. На основании загруженной коллекции (структуры)
     * производить вычисления
     */
    public Repository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            try {
                String[] split = strLine.split(";");
                Employee employee = new Employee(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                ArrayList<Employee> employeeArrayList = integerEmployeeMap.getOrDefault(employee.getNumber(), new ArrayList<>());
                employeeArrayList.add(employee);
                integerEmployeeMap.put(employee.getNumber(), employeeArrayList);
            } catch (RuntimeException ignored) {
            }
        }
    }

    /**
     * getMaxScores
     * Вернуть рейтинг самых успешных сотрудников в каждом отделе
     */
    public HashMap<Integer, Integer> getMaxScores() {
        HashMap<Integer, Integer> res = new HashMap<>();
        for (var entry : this.integerEmployeeMap.entrySet()) {
            int key = entry.getKey();
            ArrayList<Employee> employeeArrayList = entry.getValue();
            int max = Integer.MIN_VALUE;
            for (Employee employee1 : employeeArrayList) {
                if (employee1.getRating() > max) {
                    max = employee1.getRating();
                }
            }
            res.put(key, max);
        }
        return res;
    }

    /**
     * getCoolestEmployees
     * Определить самых успешных сотрудников по каждому отделу
     */
    public HashMap<Integer, ArrayList<Employee>> getCoolestEmployees() {
        HashMap<Integer, Integer> maxScores = this.getMaxScores();
        HashMap<Integer, ArrayList<Employee>> res = new HashMap<>();
        for (var entry : this.integerEmployeeMap.entrySet()) {
            int key = entry.getKey();
            int max = maxScores.getOrDefault(key, 0);
            ArrayList<Employee> employeeArrayList = entry.getValue();
            for (Employee employee1 : employeeArrayList) {
                if (employee1.getRating() == max) {
                    res.put(max, employeeArrayList);
                }
            }
        }
        return res;
    }

    /**
     * Возвращает средний рейтинг сотрудника
     */
    /*public double averageRatings(){
        double sum = 0;
        for (var entry : this.integerEmployeeMap.entrySet()) {
            ArrayList<Employee> employeeArrayList = entry.getValue();
            for (Employee employee1 : employeeArrayList){
                sum += employee1.getRating();
            }
        }
        return sum / employee.getRating();
    }*/

    /**
     * getAverageScores
     * Вернуть в виде коллекции средний рейтинг сотрудников по каждому отделу
     */
    public HashMap<Integer, Double> getAverageScores() {
        HashMap<Integer, Double> res = new HashMap<>();

        for (var entry : this.integerEmployeeMap.entrySet()) {
            double val = 0;
            ArrayList<Employee> employeeArrayList = entry.getValue();
            for (Employee employee1 : employeeArrayList) {
                val += employee1.getRating();
            }
            val /= employeeArrayList.size();
            res.put(entry.getKey(), val);
        }
        return res;
    }

    /**
     * getCountCoolestEmployees
     * Определить количество самых успешных сотрудников по каждому отделу
     */
    public HashMap<Integer, Integer> getCountCoolestEmployees() {
        HashMap<Integer, Integer> maxScores = getMaxScores();
        HashMap<Integer, Integer> res = new HashMap<>();
        for (var val : this.integerEmployeeMap.entrySet()) {
            int count = 0;
            int key = val.getKey();
            int max = maxScores.getOrDefault(key, 0);
            ArrayList<Employee> employees = val.getValue();
            for (Employee employee1 : employees) {
                if (max == employee1.getRating()) {
                    count++;
                }
            }
            res.put(count, count);
        }
        return res;
    }

    /**
     * getMaxScoreAll
     * Определить рейтинг самых успешных сотрудников по всем отделам
     */
    public int getMaxScoreAll() {
        int max = Integer.MIN_VALUE;
        for (var entry : this.integerEmployeeMap.entrySet()) {
            ArrayList<Employee> employeeArrayList = entry.getValue();
            for (Employee employee1 : employeeArrayList) {
                if (employee1.getNumber() > max) {
                    max = employee1.getNumber();
                }
            }
        }
        return max;
    }

    /**
     * getCoolestEmployeesAll
     * Определить самых успешных сотрудников по всем отделам
     */
    public ArrayList<Employee> getCoolestEmployeesAll() {
        int max = getMaxScoreAll();
        ArrayList<Employee> res = new ArrayList<>();
        for (var entry : this.integerEmployeeMap.entrySet()) {
            ArrayList<Employee> employeeArrayList = entry.getValue();
            for (Employee employee1 : employeeArrayList) {
                if (employee1.getNumber() == max) {
                    res.add(employee1);
                }
            }
        }
        return res;
    }

    /**
     * getMaxCountDepartments
     * Вернуть в порядке возрастания номера департаментов, где работает больше всего сотрудников
     */
    public HashSet<Integer> getMaxCountDepartments() {
        int max = Integer.MIN_VALUE;
        for (var val : this.integerEmployeeMap.entrySet()) {
            int valInt = val.getValue().size();
            if (valInt > max) {
                max = valInt;
            }
        }
        HashSet<Integer> res = new HashSet<>();
        for (var val : this.integerEmployeeMap.entrySet()) {
            int key = val.getKey();
            if (key == max) {
                res.add(key);
            }
        }
        return res;
    }

    /**
     * getMinCountDepartments
     * Вернуть в порядке возрастания номера департаментов, где работает меньше всего сотрудников
     */
    public HashSet<Integer> getMinCountDepartments() {
        int min = Integer.MAX_VALUE;
        for (var val : this.integerEmployeeMap.entrySet()) {
            int valInt = val.getValue().size();
            if (valInt < min) {
                min = valInt;
            }
        }
        HashSet<Integer> res = new HashSet<>();
        for (var val : this.integerEmployeeMap.entrySet()) {
            int key = val.getKey();
            if (key == min) {
                res.add(key);
            }
        }
        return res;
    }

    /**
     * sort
     * Отсортировать коллекцию сотрудников по фамилии, при равенстве фамилии по имени
     */
    public ArrayList<Employee> sort(Comparator<Employee> employeeComparator) {
        ArrayList<Employee> res = new ArrayList<>();
        for (var entry : this.integerEmployeeMap.entrySet()) {
            res.addAll(entry.getValue());
        }
        res.sort(employeeComparator);
        return res;
    }

    /**
     * maxAverageScoreDepartments
     * Вычислить в порядке возрастания номера департаментов,
     * средний рейтинг сотрудников которых максимален
     */
    public HashSet<Integer> maxAverageScoreDepartments() {
        HashMap<Integer, Double> average = getAverageScores();
        double max = 0;
        for (var entry : average.entrySet()) {
            double value = entry.getValue();
            if (value > max) {
                max = value;
            }
        }
        HashSet<Integer> res = new HashSet<>();
        for (var entry : average.entrySet()) {
            double value = entry.getValue();
            int key = entry.getKey();
            if (value == max) {
                res.add(key);
            }
        }
        return res;
    }


    @Override
    public String toString() {
        return "Repository{" +
                "integerEmployeeMap=" + integerEmployeeMap +
                '}';
    }
}
