package com.abdullaevaziz.program;



import com.abdullaevaziz.repository.IntegersRepository;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        try {
            IntegersRepository integersRepository = new IntegersRepository("input.txt");
            System.out.println(integersRepository);

            int a = integersRepository.maxCountRepeat();
            System.out.println(a);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        IntegersRepository integersRepository = new IntegersRepository();
        integersRepository.add(1);
        integersRepository.add(2);
        integersRepository.add(3);
        integersRepository.add(4);
        integersRepository.add(5);
        integersRepository.add(6);
        integersRepository.add(7);
        integersRepository.add(7);
        integersRepository.add(888);
        integersRepository.add(8);
        integersRepository.add(9);
        integersRepository.add(10);
        integersRepository.add(77);

        try {
            integersRepository.save("out.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }




        System.out.println(integersRepository);

        integersRepository.removeEqualNumbers();

        System.out.println(integersRepository);
    }
}