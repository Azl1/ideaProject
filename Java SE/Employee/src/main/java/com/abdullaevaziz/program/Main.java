package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Employee;
import com.abdullaevaziz.util.Repository;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        try {
            Repository repository = new Repository("input.txt");
            System.out.println();
            System.out.println(repository);
            System.out.println();
            System.out.println("1------------------------------------------------------");
            HashMap<Integer, Integer> res1 = repository.getMaxScores();
            System.out.println(res1);
            System.out.println();
            System.out.println("2------------------------------------------------------");
            HashMap<Integer, ArrayList<Employee>> res2 = repository.getCoolestEmployees();
            System.out.println(res2);
            System.out.println();
            System.out.println("3------------------------------------------------------");
            HashMap<Integer, Double> res3 = repository.getAverageScores();
            System.out.println(res3);
            System.out.println();
            System.out.println("4------------------------------------------------------");
            HashMap<Integer, Integer> res4 = repository.getCountCoolestEmployees();
            System.out.println(res4);
            System.out.println();
            System.out.println("5------------------------------------------------------");
            int res5 = repository.getMaxScoreAll();
            System.out.println(res5);
            System.out.println();
            System.out.println("6------------------------------------------------------");
            ArrayList<Employee> res6 = repository.getCoolestEmployeesAll();
            System.out.println(res6);
            System.out.println();
            System.out.println("7------------------------------------------------------");
            HashSet<Integer> res7 = repository.getMaxCountDepartments();
            System.out.println(res7);
            System.out.println();
            System.out.println("8------------------------------------------------------");
            HashSet<Integer> res8 = repository.getMinCountDepartments();
            System.out.println(res8);
            System.out.println();
            System.out.println("9------------------------------------------------------");
            ArrayList<Employee> res9 = repository.sort(null);
            System.out.println(res9);

            /**
             * sort
             * Отсортировать коллекцию сотрудников:
             * •По убыванию рейтинга
             * •При равных значениях рейтинга - по фамилии в лексикографическом порядке
             * •При совпадении рейтинга и фамилии - по имени в лексикографическом порядке
             * Объединить сортировки из 12 и 13 пунктов в единый метод, используя перечисления(enums)
             */
            Comparator<Employee> comparatorEmp = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    if (o1.getRating() == o2.getRating()) {
                        if (o1.getFamily().equals(o2.getFamily())) {
                            return o1.getName().compareTo(o2.getName());
                        }
                        return o1.getFamily().compareTo(o2.getFamily());
                    }
                    return -Integer.compare(o1.getRating(), o2.getRating());
                }
            };
            System.out.println();
            System.out.println("10------------------------------------------------------");
            ArrayList<Employee> res10 = repository.sort(comparatorEmp);
            System.out.println(res10);
            System.out.println();
            System.out.println("11------------------------------------------------------");
            HashSet<Integer> res11 = repository.maxAverageScoreDepartments();
            System.out.println(res11);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}