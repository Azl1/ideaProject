package com.abdullaevaziz.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * ➖написать репозиторий в котором каждая задача будет выполняться в отдельном статик методе
 * и каждый такой метод принимает на вход стринг - имя файла
 * ➖используя класс Files выполнить задачи:
 */
public class Repository {

    /**
     * ➖из файла отобрать все строки у которых количество символов равно 5
     */
    public static List<String> addStringFive (String fileName) throws IOException {

        List<String> strings = Files.lines(Path.of(fileName)).filter(x-> x.length() == 5).toList();

        return strings;
    }

    /**
     * ➖из файла отобрать все строки которые являются словами;
     */
    public static List<String> strings(String fileName) throws IOException {
        List<String> strLines = Files.lines(Paths.get(fileName)).filter(StringUtil::isWord).toList();
        return strLines;
    }

    /**
     * ➖из файла отобрать все строки с четным количесвтом символов
     */
    public static List<String> evenString (String fileName) throws IOException {
        Path pathFile = Path.of(fileName);
        List<String> stringsEven = Files.lines(pathFile).filter(x-> x.length() % 2 == 0).toList();

        return stringsEven;
    }

    /**
     * ➖из файла отобрать все строки у которых длина максимальная
     */
    public static List<String> maxString (String fileName) throws IOException {
        Path pathFile = Path.of(fileName);

        int max = Files.lines(pathFile).mapToInt(Integer::parseInt).max().orElse(0);
        List<String> stringsEven = Files.lines(pathFile).filter(x->x.length() == max).toList();
        return stringsEven;
    }
}
