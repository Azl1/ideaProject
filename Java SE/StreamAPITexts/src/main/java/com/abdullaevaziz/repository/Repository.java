package com.abdullaevaziz.repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {

    private List<String> list = new ArrayList<>();

    /**
     * 1) Прочитать файл построчно, записав в поле класса список всех строк, которые есть в файле
     */
    public Repository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }
        bufferedReader.close();
    }

    /**
     * 2) Оставить в списке только непустые строки
     */
    public void removeEmpty(){
        this.list = this.list.stream().filter(x -> !x.equals("")).toList();
    }

    /**
     * 3) В списке оставить только латинские буквы и пробелы. Прочие символы удалить
     */
    public void removeLatinLetter(){
        this.list = this.list.stream().map(x -> x.replaceAll("[^a-zA-Z ]", "")).toList();
    }

    /**
     * 4) Объединить список в единую строку, реализовав метод toString
     */
    @Override
    public String toString() {
        return this.list.stream().collect(Collectors.joining("\n")).toString();
    }

    /**
     * 5) Подсчитать количество вхождений различных слов в тескте. Подсчет вести в словаре
     */
    public Map<String, Long> count() {
        Map<String, Long> resMap1 = this.list.stream().map(x -> x.split(" ")).flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        return resMap1;
    }

    /**
     * 6) Вычислить 10 наиболее популярных и наименее популярных слов
     * (пример вывода: “ 1) -- hello -- 15”), вернув List<List<Map.Entry<String, Long>>>
     */
    public List<List<Map.Entry<String, Long>>> popular() {
        Map<String, Long> map = count();

        List<Map.Entry<String, Long>> result1 = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());

        List<Map.Entry<String, Long>> result2 = map.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .limit(10)
                .collect(Collectors.toList());

        return List.of(result1, result2);
    }

    /**
     * 7) Заменить наименее популярные слова на “PYTHON”
     */
    public List<String> replacingWords(){
        List<List<Map.Entry<String, Long>>> entryList = popular();
        List<Map.Entry<String, Long>> res = entryList.get(0);

        List<String> words =  res.stream().map(Map.Entry::getKey).toList();
        List<String> resPython  = this.list.stream().map(x->x.split(" ")).flatMap(Arrays::stream).map(x->{
            if(words.contains(x)){
                return "PYTHON";
            }
            else{
                return x;
            }
            }).toList();

        return resPython;
    }

    /**
     * 8) написать метод, который вернет список всех слов которые
     * встречаются максимальное количество раз в списке репозитория
     */
    public List<Map.Entry<String, Long>> maxWord(){

        Map<String, Long> resMap2 = count();

        long max = resMap2.entrySet().stream()
                .mapToLong(Map.Entry::getValue).max().orElse(0L);


        return resMap2.entrySet().stream().filter(x->x.getValue() == max).toList();
    }

}
