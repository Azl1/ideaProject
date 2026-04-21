package repository;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class StringRepository {

    private String str;

    public StringRepository(String fileName) throws IOException {
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName))){
            byte[] bytes = bufferedInputStream.readAllBytes();
            System.out.println(Arrays.toString(bytes));
            this.str = new String(bytes/*, Charset.forName("windows-1251")*/);
        }
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void save(String fileName) throws IOException {
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))){
            bufferedOutputStream.write(this.str.getBytes(/*Charset.forName("windows-1251")*/));
        }
    }

    @Override
    public String toString() {
        return "StringRepository{" +
                "str='" + str + '\'' +
                '}';
    }
}
