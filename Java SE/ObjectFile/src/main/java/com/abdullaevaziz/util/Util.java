package com.abdullaevaziz.util;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class Util {

    private File file;

    public Util(String fileName) {
        this.file = new File(fileName);
    }


    /**
     * Путь к папке приходит из аргументов командной строки.
     * Вывести на экран количество файлов и папок которые в ней есть
     */
    public int countFileLength() {
        return this.file.listFiles().length;
    }

    public long countFile() {
        return Arrays.stream(this.file.listFiles()).filter(File::isDirectory).count();
    }


    public Stream<File> countFileTxt() {
       return Arrays.stream(this.file.listFiles()).filter(x -> x.isFile() && x.getName().endsWith(".txt"));
    }

    public boolean newNamePath(File fileCopy){
        return this.file.renameTo(fileCopy);
    }


    @Override
    public String toString() {
        return "Util{" +
                "file=" + this.file +
                '}';
    }
}
