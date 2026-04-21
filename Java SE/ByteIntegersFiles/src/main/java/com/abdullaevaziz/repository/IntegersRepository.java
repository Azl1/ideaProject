package com.abdullaevaziz.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class IntegersRepository {

    private ArrayList<Integer> integerArrayList = new ArrayList<>();

    public IntegersRepository() {
    }

    /**
     * 2.Создать репозиторий IntegersRepository с конструктором по умолчанию и с конструктором,
     * который принимает на вход путь к файлу,
     * инициализирует список-поле класса значениями целых чисел из указанного файла,
     * считав его побайтово целиком, числа в файле следуют каждый с новой строки,
     * либо же через пробел. Считать изначально файл целиком, а далее произвести его сплитование
     */
    public IntegersRepository(String fileName) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] bytes = bufferedInputStream.readAllBytes();
            String str = new String(bytes);
            String[] s = str.split("[ \r\n]"); //TODO тут в кавычках написать [ \r\n]
            for (String val : s) {
                if (!val.equals("")) { //TODO тут проверка что строка не пустая то есть не равна пустым кавычкам
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
     * 4.Написать метод сохранения репозитория в указанный файл, открыв его байтовым потоком,
     * путь к которому необходимо передать как параметр метода
     */
    public void save(String fileName) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            String str = "";
            for (int val : this.integerArrayList) {
                str += val + " ";
            }
            bufferedOutputStream.write(str.getBytes());
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
     * 6.Найти максимальное количество подряд идущих чисел в списке чисел
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
     */
    public static boolean isEqualNumber(int a) {
        String t = String.valueOf(a);
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) != t.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public void removeEqualNumbers() {
        ArrayList<Integer> deleted = new ArrayList<>();
        for (int val : this.integerArrayList) {
            if (isEqualNumber(val)) {
                deleted.add(val);
            }
        }
        this.integerArrayList.removeAll(deleted);
    }
}
