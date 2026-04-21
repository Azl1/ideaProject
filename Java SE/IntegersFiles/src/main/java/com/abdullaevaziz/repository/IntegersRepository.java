package com.abdullaevaziz.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class IntegersRepository {

    private ArrayList<Integer> integerArrayList = new ArrayList<>();


    public IntegersRepository() {
    }

    /**
     * 2.Создать репозиторий IntegersRepository с конструктором по умолчанию и с конструктором,
     * который принимает на вход путь к файлу,
     * инициализирует список-поле класса значениями целых чисел из указанного файла,
     * числа в файле следуют каждый с новой строки
     */
    /**
     * 8.Модифицировать конструктор IntegersRepository таким образом,
     * чтобы он загружал все числа, которые есть в файле,
     * при этом теперь числа могут следовать как через пробел, так и с новой строки
     */
    public IntegersRepository(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] s = line.split(" ");
                for (String val : s) {
                    this.integerArrayList.add(Integer.valueOf(val));
                }
            }
        }
    }

    /**
     * 3.Написать метод добавления нового числа в список чисел
     */
    public void add(int value) {
        this.integerArrayList.add(value);
    }

    /**
     * 4.Написать метод сохранения репозитория в указанный файл,
     * путь к которому необходимо передать как параметр метода
     */
    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer line : this.integerArrayList) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }

    /**
     * 5.Написать метод строкового представления репозитория toString
     */
    @Override
    public String toString() {
        return "IntegersRepository{" +
                "integerArrayList=" + integerArrayList +
                '}';
    }

    /**
     * 6.Найти максимальное количество подряд идущих равных чисел в списке чисел
     */
    public int maxCountRepeat() {
        int count = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < this.integerArrayList.size() - 1; i++) {
            if (this.integerArrayList.get(i).equals(this.integerArrayList.get(i + 1))) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count = 1;
            }

        }
        return count;
    }

    /**
     * 7.Удалить из исходного списка все числа с одинаковыми цифрами
     * (например, такие как 1, 111, 11, 22, 33)
     */
    public static boolean isEqualNumber(int a){
        String t = String.valueOf(a);
        for (int i = 0; i < t.length(); i++) {
            if(t.charAt(i) != t.charAt(0)){
               return false;
            }
        }
        return true;
    }

    public void removeEqualNumbers() {
        ArrayList<Integer> deleted = new ArrayList<>();
        for (int val : this.integerArrayList) {
            if (isEqualNumber(val)){
                deleted.add(val);
            }
        }
        this.integerArrayList.removeAll(deleted);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegersRepository that = (IntegersRepository) o;
        return Objects.equals(integerArrayList, that.integerArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integerArrayList);
    }

}