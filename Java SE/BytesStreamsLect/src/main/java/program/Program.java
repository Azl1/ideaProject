package program;

import repository.SiteRepository;
import repository.StringRepository;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {

        try {
            /*StringRepository stringRepository = new StringRepository("Data.txt");
            System.out.println(stringRepository);
            stringRepository.save("out.txt");*/

            SiteRepository siteRepository = new SiteRepository("https://www.google.ru/");
            System.out.println(siteRepository);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
