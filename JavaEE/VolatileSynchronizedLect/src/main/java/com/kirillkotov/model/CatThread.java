package com.kirillkotov.model;

import com.kirillkotov.repository.CatRepository;

public class CatThread extends Thread{
    private Cat cat;
    private CatRepository catRepository;

    public CatThread(Cat cat, CatRepository catRepository) {
        this.cat = cat;
        this.setName("Thread " + this.cat.getName());
        this.catRepository = catRepository;
    }

    public Cat getCat() {
        return cat;
    }

    // Точка входа в поток
    @Override
    public void run() {
        System.out.printf("Кот %s идёт в бой.%n", this.cat.getName());
        // Пока котов больше 1
        while (this.catRepository.exists()) {
            // Атакуем произвольного кота из оставшихся, кроме себя
            CatThread enemyCat = this.catRepository.getRandomEnemyCat(this);
            this.attack(enemyCat);
        }
    }

    public synchronized void attack(CatThread enemyThreadCat) {
        // Дополнительная проверка жизни — во избежание конфликта (у кота может не быть жизней)
        if (this.cat.getLife() <= 0) {
            return;
        }

        // Если противник имеет жизни
        if (enemyThreadCat.cat.isAlive()) {
            // Отнимаем жизнь противника
            enemyThreadCat.cat.decrementLife();
            System.out.printf("Кот %s атаковал кота %s. Жизни %<s: %d%n", this.cat.getName(),
                    enemyThreadCat.cat.getName(), enemyThreadCat.cat.getLife());

            // Если противник теперь не имеет жизней
            if (!enemyThreadCat.cat.isAlive()) {
                // Удаляем противника из списка
                this.catRepository.remove(enemyThreadCat);

                System.out.printf("Кот %s покидает бой.%n", enemyThreadCat.cat.getName());
                System.out.printf("Оставшиеся коты: %s%n", this.catRepository);
                System.out.printf("%s завершает свою работу.%n", enemyThreadCat.getName());
                // interrupt() — прервать работу треда
                enemyThreadCat.interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return this.cat.getName();
    }
}
