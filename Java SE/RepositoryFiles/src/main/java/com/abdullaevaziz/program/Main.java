package com.abdullaevaziz.program;

import com.abdullaevaziz.repository.StringUtil;
import com.abdullaevaziz.repository.Repository;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path pathFile = Path.of("C:\\Users\\Az\\Desktop\\Java\\Word\\Wikipedia.txt");
        try {

          List<String> res1 = Repository.addStringFive(String.valueOf(pathFile));
          System.out.println(res1);


          List<String> res2 = Repository.strings(String.valueOf(pathFile));
          System.out.println(res2);



          List<String> res3 = Repository.evenString(String.valueOf(pathFile));
          System.out.println(res3);




        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}