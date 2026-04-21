package com.abdullaevaziz.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {
    private Map<Path, ArrayList<Path>> map = new HashMap<>();

    /**
     * 3. Создать репозиторий, конструктор которого принимает на вход путь
     * к директории и производит загрузку в поле класса(Map)
     * информацию обо всех папках и файлах, которые в ней есть, обходя ее рекурсивно.
     * Информация в словарь должна добавляться в следующем виде: ключ – путь к директории,
     * значение – список путей к файлам, которые в ней есть
     */


    public Repository(String fileName) throws IOException {
        Path pathDir = Path.of(fileName);

        System.out.println("\nResult of walk recursive directory");
        Files.walkFileTree(pathDir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                Path key = file.getParent();
                ArrayList<Path> arrayList = map.getOrDefault(key, new ArrayList<>());
                arrayList.add(file);
                map.put(key, arrayList);

                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return super.preVisitDirectory(dir, attrs);
            }
        });
    }

    /**
     * 4. Найти все папки с наибольшим количеством файлов
     */
    public List<Path> countFile() {
        int max = this.map.entrySet().stream().mapToInt(x -> x.getValue().size()).max().orElse(0);

        List<Path> listPath = this.map.entrySet().stream()
                .filter(x -> x.getValue().size() == max).map(x->x.getKey()).toList();


        return listPath;
    }

    /**
     * 5. Преобразовать словарь в список объектов в следующем виде:
     * путь к папке – путь к файлу из этой папки. Для хранения пар создать класс FilePair
     */
    public List<FilePair> transform(){
        List<FilePair> res = new ArrayList<>();
        for (Map.Entry<Path, ArrayList<Path>> entry : this.map.entrySet()) {
            Path key = entry.getKey();
            for (Path file : entry.getValue()) {
                FilePair filePair = new FilePair(key,file);
                res.add(filePair);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "map=" + this.map +
                '}';
    }
}
