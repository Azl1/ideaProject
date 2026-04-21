package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Garden;
import com.abdullaevaziz.model.Plant;
import com.abdullaevaziz.model.Size;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Plant plant = new Plant();
        Size size1 =  new Size(1,2,3);
        Size size2 =  new Size(4,5,6);
        Size size3 =  new Size(7,8,9);
        //Plant plant2 = new Plant("Ромашка", 5);
        Plant plant1 = new Plant("Роза", "Красная", 10, 7, 150, size1);
        Plant plant2 = new Plant("Ромашка", "Желтая", 12, 8, 200, size2);
        Plant plant3 = new Plant("Одуванчик", "Прозрачная", 14, 10, 300, size3);

        /*System.out.println("Print plant1\n" + plant1);
        System.out.println("Print plant2\n" + plant2);
        System.out.println("Print plant3\n" + plant3);*/

        /*int res = plant2.plantEnlargement(10);
        System.out.println("\nКоличество стеблей ");
        System.out.println(res);


        System.out.println();
        System.out.println(plant3.getName());
        System.out.println();
        plant3.setName("Лилия");
        String namesPlant = plant3.getName();
        System.out.println("\nНазвания растений ");
        System.out.println(namesPlant);


        System.out.println("Copy--------------------");
        Plant plantCopy1 = plant3.clone();
        System.out.println(plantCopy1);
        int plantCopy2 = plant3.clone().plantEnlargement(20);
        System.out.println("\nКоличество стеблей ");
        System.out.println(plantCopy2);

        System.out.println();
        Size sizeCopy = size1.clone();
        System.out.println(sizeCopy);*/

       /* Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Plant[] plantsMass = new Plant[n];
        for (int i = 0; i < plantsMass.length; i++) {
            plantsMass[i] = new Plant(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                    new Size(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        System.out.println(Arrays.toString(plantsMass));

        Plant[] clonedPlantsMass = new Plant[plantsMass.length];
        for (int i = 0; i < clonedPlantsMass.length ; i++) {
            clonedPlantsMass[i] = plantsMass[i].clone();
        }

        for (Plant massPlantOriginal : plantsMass) {
            System.out.println(massPlantOriginal);
        }

        for (Plant massPlantCopy : clonedPlantsMass) {
            System.out.println(massPlantCopy);
        }*/

        Garden garden = new Garden(3);
        Garden garden2 = new Garden(5);
        Garden garden3 = new Garden(8);

        //Добавление plant
        boolean addPlant1 = garden.add(plant1);
        boolean addPlant2 = garden.add(plant2);
        boolean addPlant3 = garden.add(plant3);
        System.out.println(addPlant1);
        System.out.println(addPlant2);
        System.out.println(addPlant3);

        // Получение растений по индексу
        Plant plantAtIndex1 = garden.get(0);
        Plant plantAtIndex2 = garden.get(1);
        Plant plantAtIndex3 = garden.get(2);
        System.out.println(plantAtIndex1);
        System.out.println(plantAtIndex2);
        System.out.println(plantAtIndex3);

        //Возвращение количество plant
        int countPlant = garden.count();
        System.out.println(countPlant);

        // Возвращает найденный объект
        Plant resSearch1 = garden.search("Ромашка");
        System.out.println(resSearch1);

        //удаление по имени
        Plant remove1 = garden.delete("Ромашка");

        //удаление по объекту
        boolean remove2 = garden.delete(plant2);

        //вставка объекта
        boolean resInsert = garden.insert(1, plant3);

        Garden gardenClone = garden.clone();
        boolean addRes1 = gardenClone.add(plant);
        boolean addRes2 = gardenClone.add(plant1);
        boolean addRes3 = gardenClone.add(plant2);
        boolean addRes4 = gardenClone.add(plant3);
        System.out.println(addRes1);
        System.out.println(addRes2);
        System.out.println(addRes3);
        System.out.println(addRes4);
    }
}