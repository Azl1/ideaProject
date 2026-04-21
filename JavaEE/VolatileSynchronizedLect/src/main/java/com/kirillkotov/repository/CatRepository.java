package com.kirillkotov.repository;

import com.kirillkotov.model.CatThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CatRepository implements Iterable<CatThread>{
    private List<CatThread> catThreads = new CopyOnWriteArrayList<>();

    public void add(CatThread catThread){
        this.catThreads.add(catThread);
    }

    public boolean exists() {
        return this.catThreads.size() > 1;
    }

    public CatThread getRandomEnemyCat(CatThread deleteThisCat) {
        // Создаём лист-копию из основного листа cats
        List<CatThread> copyCats = new ArrayList<>(this.catThreads);
        // Удаляем текущего кота, чтобы он не выпал в качестве противника
        copyCats.remove(deleteThisCat);
        // Возвращаем произвольного кота из оставшихся с помощью класса util.java.Random
        return copyCats.get(new Random().nextInt(copyCats.size()));
    }

    public void remove(CatThread enemyCat) {
        this.catThreads.remove(enemyCat);
    }

    public CatThread getFirst() {
        return this.catThreads.get(0);
    }

    @Override
    public Iterator<CatThread> iterator() {
        return this.catThreads.iterator();
    }

    @Override
    public String toString() {
        return "CatRepository{" +
                "cats=" + catThreads +
                '}';
    }
}
