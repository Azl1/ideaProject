package com.abdullaevaziz.program;

import com.abdullaevaziz.util.FilePair;
import com.abdullaevaziz.util.Repository;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /**
         * 1. Создать проект WalkDirectory
         * 2. Путь к директории подается как аргумент командной строк
         */
        try {
            Repository repository = new Repository("C:\\Users\\Az\\Desktop\\Java");
            List<Path> res1 = repository.countFile();
            System.out.println(res1);

            List<FilePair> res2 = repository.transform();
            System.out.println(res2);
            System.out.println("--------------------------------");
            String string = repository.toString();
            System.out.println(string);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}