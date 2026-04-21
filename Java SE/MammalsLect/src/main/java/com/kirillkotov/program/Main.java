package com.kirillkotov.program;

import com.kirillkotov.model.Cat;
import com.kirillkotov.model.Mammal;

public class Main {
    public static void f(Mammal mammal){
        mammal.voice();
    }

    public static void main(String[] args) {
        //Mammal mammal = new Mammal("Vasya", "Black", 10, 20); //Не работает для абстрактных классов
        Cat cat = new Cat("Vasya", "Black", 10, 20, 100);
        System.out.println(cat);

        cat.voice();

        Mammal mammal1 = new Cat("Vasya", "Black", 10, 20, 100);

        f(mammal1);
        f(cat);

        if(mammal1.getClass() == Cat.class){
            Cat cat1 = (Cat) mammal1;
            int mouseExperience = cat1.getMouseExperience();
            System.out.println(mouseExperience);
        }

        if(mammal1 instanceof Cat cat1){
            int mouseExperience = cat1.getMouseExperience();
            System.out.println(mouseExperience);
        }
    }
}