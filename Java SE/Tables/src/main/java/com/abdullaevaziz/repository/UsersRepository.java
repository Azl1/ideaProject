package com.abdullaevaziz.repository;

import com.abdullaevaziz.modelData.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UsersRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * 3. Создать репозиторий, конструктор которого принимает на вход объект
     * типа File и производит инициализацию списка пользователей из этого файла.
     * Далее в этом конструкторе проставить для каждого объекта в поле isSend значение true,
     * если его id есть в списке репозитория SendUserIdRepository
     */

    private ArrayList<User> userArrayList = new ArrayList<>();

    public UsersRepository() {
    }

    public UsersRepository(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            this.userArrayList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
            SendUserIdRepository sendUserIdRepository = new SendUserIdRepository();
            for (User users : this.userArrayList) {
                if (sendUserIdRepository.contains(users.getId())) {
                    users.setSend(true);
                }
            }
        }
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRepository that = (UsersRepository) o;
        return Objects.equals(userArrayList, that.userArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userArrayList);
    }

    @Override
    public String toString() {
        return "UsersRepository{" +
                "userArrayList=" + userArrayList +
                '}';
    }
}
