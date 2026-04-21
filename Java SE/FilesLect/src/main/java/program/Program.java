package program;

import repository.Repository;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        /*try {
            Repository repository = new Repository("input.txt");

            System.out.println(repository);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/

        Repository repository = new Repository();
        repository.add("wew");
        repository.add("wew4");
        repository.add("we3w");
        repository.add("w2ew");

        try {
            repository.save("out.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
