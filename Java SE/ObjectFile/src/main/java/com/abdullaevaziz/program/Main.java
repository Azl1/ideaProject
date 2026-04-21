package com.abdullaevaziz.program;

import com.abdullaevaziz.util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        Util util1 = new Util("C:\\Users\\Az\\Desktop\\Java\\1");
        int res1 = util1.countFileLength();
        System.out.println(res1);
        long res2 = util1.countFile();
        System.out.println(res2);

        Stream<File> res3 = util1.countFileTxt();
        System.out.println(Arrays.toString(res3.toArray()));

        System.out.println("----------------------------------");
        File fileDir1 = new File("C:\\Users\\Az\\Desktop\\Java\\5");

        boolean res4 = util1.newNamePath(fileDir1);
        System.out.println(res4);
    }
}