package com.abdullaevaziz;

import com.abdullaevaziz.model.Human;
import com.abdullaevaziz.model.University;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Human human = new Human();
        Human human1 = new Human("Иванов", 20, 500, 52);
        Human human2 = new Human("Петров", 25, 8000, 55);
        Human human3 = new Human("Сидоров", 20, 400, 52);
        Human human4 = new Human("Александров", 25, 11000,58);
        Human human5 = new Human("Романов", 20, 11000, 52);
        Human human6 = new Human("Березин", 25, 700,60);
        Human human7 = new Human("Васильев", 28, 555,65);


        //TODO создать класс университет с полми название адрес
        // и  список людей в консрукторы списки не добавляй
        System.out.println();
        University university = new University();
        University university1 = new University("Унивиситет 2035", "Москва");
        University university2 = new University("Московский финансово-юридический университет", "Королев");

        System.out.println(university1);
        System.out.println(university2);

        System.out.println();
        university1.addHuman(human1);
        university1.addHuman(human2);
        university1.addHuman(human3);
        university1.addHuman(human4);
        university1.addHuman(human5);
        university1.addHuman(human6);
        university1.addHuman(human7);

        System.out.println(university1);

        int maxSalary = university1.maxSalary();
        System.out.println(maxSalary);
        //TODO найти всех людей с максимальныой зп
        System.out.println("Все люди с максимальныой зп");
        ArrayList<Human> maxSalaryAll = university1.maxSalaryAll();
        System.out.println(maxSalaryAll);
        System.out.println();
        //TODO сделать метод удаления человека
        System.out.println("Метод удаления человека");
        boolean removeHuman = university1.remove(human6);
        System.out.println(removeHuman);
        System.out.println();
        //TODO сделать метод обновления человека
        System.out.println("Метод обновления человека");
        ArrayList<Human> update = university1.updateHuman(human7);
        System.out.println(update);
        System.out.println();
        //TODO метод получения человека по имени
        System.out.println("метод получения человека по имени");
        Human humanGet = university1.humanGet("Петров");
        System.out.println(humanGet);
        System.out.println();
        //TODO получение вскех людей из универа
        System.out.println("метод получение вскех людей из универа");
        ArrayList<Human> humanGetAll = university1.humanGetAll();

        System.out.println(humanGetAll);


    }
}