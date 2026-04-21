package com.kirillkotov.program;

import com.kirillkotov.model.TV;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("123");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1234");
        arrayList.add("12");
        System.out.println("Print new List of strings length = 2");
        List<String> res1 = arrayList.stream().filter(x -> x.length() == 2).toList();
        System.out.println(res1);

        System.out.println("\nPrint new List of integers");
        List<Integer> res2 = arrayList.stream().map(x -> Integer.parseInt(x)).toList();
        System.out.println(res2);

        System.out.println("\nPrint new List of integers");
        List<Integer> res3 = arrayList.stream().map(Integer::parseInt).filter(x -> x % 2 == 0).toList();
        System.out.println(res3);

        boolean res4 = arrayList.stream().map(Integer::parseInt).allMatch(x -> x == 10);
        System.out.println("\nResult of all match");
        System.out.println(res4);

        boolean res5 = arrayList.stream().map(Integer::parseInt).anyMatch(x -> x % 2 == 0);
        System.out.println("\nResult of any match");
        System.out.println(res5);

        long count = arrayList.stream().map(Integer::parseInt).filter(x -> x % 2 == 0).count();
        System.out.println("\nResult of count");
        System.out.println(count);

        List<String> res6 = arrayList.stream().distinct().toList();
        System.out.println("\nResult of distinct");
        System.out.println(res6);

        Integer res7 = arrayList.stream().map(Integer::parseInt).filter(x -> x % 2 == 0).findFirst().orElse(null);
        System.out.println("\nResult of first element");
        System.out.println(res7);

        List<Integer> res8 = arrayList.stream().map(Integer::parseInt).filter(x -> x % 2 == 0).limit(2).toList();
        System.out.println("\nResult of limit 2 elements");
        System.out.println(res8);

        List<Integer> res9 = arrayList.stream().map(Integer::parseInt).filter(x -> x % 2 == 0).skip(2).toList();
        System.out.println("\nResult of skip 2 elements");
        System.out.println(res9);

        Integer[] integers = arrayList.stream().map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println("\nResult of array");
        System.out.println(Arrays.toString(integers));

        List<Integer> sorted = arrayList.stream().map(Integer::parseInt).sorted().toList();
        System.out.println("\nResult of sorted list");
        System.out.println(sorted);

        List<Integer> sorted1 = arrayList.stream().map(Integer::parseInt).sorted(Collections.reverseOrder()).toList();
        System.out.println("\nResult of sorted list desc");
        System.out.println(sorted1);

        int max = arrayList.stream().mapToInt(Integer::parseInt).max().orElse(0);
        System.out.println("\nResult of max");
        System.out.println(max);

        int sum = arrayList.stream().mapToInt(Integer::parseInt).sum();
        System.out.println("\nResult of sum");
        System.out.println(sum);

        double avg = arrayList.stream().mapToInt(Integer::parseInt).average().orElse(0);
        System.out.println("\nResult of avg");
        System.out.println(avg);

        String[] arr = {"abc", "abd", "cda", "a"};
        String[] strings = Arrays.stream(arr).map(x -> x.split("")).flatMap(Arrays::stream).toArray(String[]::new);
        System.out.println("\nArray of all symbols");
        System.out.println(Arrays.toString(strings));

        String res10 = Arrays.stream(arr).map(x -> x.split("")).flatMap(Arrays::stream)
                .collect(Collectors.joining(" "));
        System.out.println("\nResult of string elements");
        System.out.println(res10);

        Set<String> res11 = Arrays.stream(arr).map(x -> x.split("")).flatMap(Arrays::stream)
                .collect(Collectors.toSet());
        System.out.println("\nResult of set elements");
        System.out.println(res11);

        Map<String, Long> res12 = Arrays.stream(arr).map(x -> x.split("")).flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println("\nResult of map elements");
        System.out.println(res12);

        HashMap<String, List<Integer>> hashMap = new HashMap<>();
        hashMap.put("Hello", List.of(1, 4, 5, 7));
        hashMap.put("World", List.of(1, 4));
        hashMap.put("Palych", List.of(1, 4, 5));
        List<Map.Entry<String, List<Integer>>> entries1
                = hashMap.entrySet().stream().sorted(Comparator.comparingInt(x -> x.getValue().size())).toList();
        System.out.println("\nResult of list sorted map");
        System.out.println(entries1);

        LinkedHashMap<String, List<Integer>> linkedHashMap = hashMap.entrySet().stream().
                sorted(Comparator.comparingInt(x -> x.getValue().size())).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
        System.out.println("\nResult of LinkedHashMap");
        System.out.println(linkedHashMap);

        List<TV> tvs = List.of(
                new TV("Samsung", "A230","black",5500, 230 ),
                new TV("Samsung", "A500","white",7500, 450 ),
                new TV("Panasonic","GR","grey",5700,430),
                new TV("Sony","G750","black",7500,475),
                new TV("Benq","GL951","black",7000,3500),
                new TV("Samsung", "A230","black",5500, 230 ),
                new TV("Toshiba","A5","grey",5000,550),
                new TV("Sony","G750","black",7500,475),
                new TV("Samsung", "A230","black",5500, 230 ),
                new TV("LG","DQ500","white",7000,600)
        );

        Map<String, List<TV>> multiMap = tvs.stream().collect(Collectors.groupingBy(TV::getBrand,
                Collectors.mapping(x -> x, Collectors.toList())));
        System.out.println(multiMap);
    }
}