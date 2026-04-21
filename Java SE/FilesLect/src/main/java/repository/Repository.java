package repository;

import java.io.*;
import java.util.ArrayList;

public class Repository {
    private ArrayList<String> strings = new ArrayList<>();

    public Repository() {
    }

    public Repository(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                this.strings.add(line);
            }
        }
    }

    public void add(String value) {
        this.strings.add(value);
    }

    public void save(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : this.strings) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public String toString() {
        return "Repository{" +
                "strings=" + strings +
                '}';
    }
}
