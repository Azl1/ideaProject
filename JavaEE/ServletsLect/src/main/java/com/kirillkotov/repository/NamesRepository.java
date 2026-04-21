package com.kirillkotov.repository;

import com.kirillkotov.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NamesRepository {
    private List<String> names = new ArrayList<>();

    public NamesRepository() {
        try {
            names = Files.readAllLines(Path.of(Constants.FILE_DB));
        } catch (IOException ignored) {}
    }

    private void save(){
        try {
            Files.write(Path.of(Constants.FILE_DB), this.names);
        } catch (IOException ignored) {}
    }

    public void add(String name){
        this.names.add(name);
        this.save();
    }
}
