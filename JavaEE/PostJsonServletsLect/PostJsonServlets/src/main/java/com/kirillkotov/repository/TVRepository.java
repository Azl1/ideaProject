package com.kirillkotov.repository;

import com.kirillkotov.model.TV;

import java.util.ArrayList;

public class TVRepository {
    private static long nextId = 1;
    private ArrayList<TV> tvs = new ArrayList<>();

    public void add(TV tv){
        tv.setId(nextId++);
        this.tvs.add(tv);
    }

    public boolean update(TV tv) {
        TV old = this.tvs.stream().filter(x -> x.getId() == tv.getId()).findFirst().orElse(null);
        if(old == null){
            return false;
        }
        int i = this.tvs.indexOf(old);
        this.tvs.set(i, tv);
        return true;
    }

    public ArrayList<TV> getTvs() {
        return tvs;
    }
}
