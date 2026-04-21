package com.kirillkotov.service;

import com.kirillkotov.model.Cat;
import com.kirillkotov.model.CatThread;
import com.kirillkotov.repository.CatRepository;

public class CatService {
    private CatRepository catRepository = new CatRepository();

    public void add(Cat cat) {
        CatThread thread = new CatThread(cat, this.catRepository);
        this.catRepository.add(thread);
        System.out.printf("Кот %s создан. HP: %d%n", cat.getName(), cat.getLife());
    }

    public Cat start() {
        // Запускаем котов
        for (CatThread catThread : this.catRepository) {
            catThread.start();
        }

        try {
            // Ждём, пока завершатся все, кроме главного
            for (CatThread catThread : this.catRepository) {
                // Поток, который вызвал метод join(), приостанавливается на этой строчке
                catThread.join();
                // Пока поток, на котором вызван метод, не завершит работу, Main ждёт остальных
            }
        } catch (InterruptedException ignored) {}

        // Последний выживший — первый элемент в репозитории
        return this.catRepository.getFirst().getCat();
    }

}
