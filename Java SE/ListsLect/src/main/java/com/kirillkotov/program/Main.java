package com.kirillkotov.program;

import com.kirillkotov.util.ArrayUtil;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int[] mass = {1, 10, 1, 4, 1};
        ArrayList<Integer> res = ArrayUtil.search(mass, 1);
        System.out.println("Result of search: " + res);

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        arrayList.add(1);
        arrayList.add(10);
        arrayList.add(10);
        arrayList.add(1000);
        arrayList.add(2000);
        arrayList.add(100);
        System.out.println("\nPrint ArrayList after add to back: " + arrayList);

        linkedList.add(1);
        linkedList.add(10);
        System.out.println("\nPrint LinkedList after add to back: " + linkedList);

        arrayList.add(1, 100);
        System.out.println("\nPrint ArrayList after add to index: " + arrayList);

        Integer deleted = 100;
        arrayList.remove(deleted);
        System.out.println("\nPrint ArrayList after delete element: " + arrayList);

        arrayList.remove(0);
        System.out.println("\nPrint ArrayList after delete index: " + arrayList);

        int i = arrayList.indexOf(10);
        System.out.println("\nResult of search element: " + i);

        int i1 = arrayList.indexOf(800);
        System.out.println("\nResult of search element if not exists: " + i1);

        boolean contains = arrayList.contains(10);
        System.out.println("\nResult of check element exists: " + contains);

        Integer value = arrayList.get(0);
        System.out.println("\nResult of get element by index: " + value);

        System.out.println("\nIterate for ArrayList by for-each");
        for (int j = 0; j < arrayList.size(); j++) {
            System.out.print(arrayList.get(j) + " ");
        }

        System.out.println("\n\nIterate for ArrayList by for-each");
        for (int val : arrayList) {
            System.out.print(val + " ");
        }

    }
}