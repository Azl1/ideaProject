package com.abdullaevaziz;

import com.abdullaevaziz.model.Client;
import com.abdullaevaziz.model.ProstituteIndividual;
import com.abdullaevaziz.repository.ClientRepository;
import com.abdullaevaziz.repository.ProstituteRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

       /* try {
           ClientRepository clientRepository = new ClientRepository();
           boolean addRes1 = clientRepository.add(
           new Client("Иванов", 8345, 28, "Предпочитает лесбиянок"));

            boolean addRes2 = clientRepository.add(
                    new Client("Петров", 78345, 23, "Предпочитает тройничок"));

            boolean addRes3 = clientRepository.add(
                    new Client("Сидоров", 58345, 25, "Предпочитает дрочить"));

            boolean addRes4 = clientRepository.add(
                    new Client("Романов", 56345, 22, "Предпочитает быть успешным"));

            boolean addRes5 = clientRepository.add(
                    new Client("Березин", 81345, 20, "Предпочитает быть крутым"));
            System.out.println(addRes1);
            System.out.println(addRes2);
            System.out.println(addRes3);
            System.out.println(addRes4);
            System.out.println(addRes5);
            List<Client> list1 = clientRepository.getClients();
            System.out.println(list1);

            Client clientGetRes1 = clientRepository.getId(1);
            Client clientGetRes2 = clientRepository.getId(2);
            System.out.println();
            System.out.println(clientGetRes1);
            System.out.println(clientGetRes2);

            boolean clientDelete = clientRepository.delete(clientGetRes1);
            System.out.println(clientDelete);
            System.out.println();

            boolean clientUpdate = clientRepository.update(new Client
                    (5,"Букин", 81345, 25, "Предпочитает курить сигару"));
            System.out.println(clientUpdate);


            System.out.println("-----------------------------------------------------------------------");

            ProstituteRepository prostituteRepository = new ProstituteRepository();
            boolean prostituteAddRes1 = prostituteRepository.add(
                    new ProstituteIndividual("Воронова", 25, 56, "Берет в рот",1, 2));

            boolean prostituteAddRes2 = prostituteRepository.add(
                    new ProstituteIndividual("Акимова ", 21, 55, "дает в жопу",1.3, 1));

            boolean prostituteAddRes3 = prostituteRepository.add(
                    new ProstituteIndividual("Дьякова ", 22, 57, "целует ноги и половой орган",1.5, 3));

            boolean prostituteAddRes4 = prostituteRepository.add(
                    new ProstituteIndividual("Ежова", 23, 52, "дает сразу и везде", 1.2, 4));

            boolean prostituteAddRes5 = prostituteRepository.add(
                    new ProstituteIndividual("Гурова", 20, 50, "любит наказывать подкаблучников", 2, 5));
            System.out.println(prostituteAddRes1);
            System.out.println(prostituteAddRes2);
            System.out.println(prostituteAddRes3);
            System.out.println(prostituteAddRes4);
            System.out.println(prostituteAddRes5);
            List<ProstituteIndividual> list2 = prostituteRepository.getProstitutes();
            System.out.println(list2);

         ProstituteIndividual prostituteGetRes1 = prostituteRepository.getId(1);
         ProstituteIndividual prostituteGetRes2 = prostituteRepository.getId(2);
            System.out.println();
            System.out.println(prostituteGetRes1);
            System.out.println(prostituteGetRes2);

            boolean prostituteDelete = prostituteRepository.delete(prostituteGetRes1);
            System.out.println(prostituteDelete);
            System.out.println();

            boolean prostituteUpdate = prostituteRepository.update(new ProstituteIndividual
                    (3,"Казакова ", 20, 50, "любит наказывать подкаблучников", 3,4));
            System.out.println(prostituteUpdate);

        } catch (Exception e) {
            e.printStackTrace();
        } */

    }
}