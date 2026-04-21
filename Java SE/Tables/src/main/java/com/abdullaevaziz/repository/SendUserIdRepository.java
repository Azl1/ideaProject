package com.abdullaevaziz.repository;

import com.abdullaevaziz.modelData.User;
import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class SendUserIdRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    private ArrayList<Integer> userIdsList = new ArrayList<>();

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 2. Создать репозиторий SendUserIdRepository,
     * конструктор которого не принимает на вход аргументов,
     * производит загрузку данных в список целых чисел из файла
     * формата JSON send.json. Имя данного файла сделать
     * статической константой в классе Constants пакета util.
     * Файл вручную создавать не надо, изначально его вообще не будет существовать,
     * ваша программа в будущем сама будет его создавать в случае отсутствия
     * и добавлять в него нужные ей данные в п. 6.
     * Исключение в конструкторе об отсутствии файла необходимо заигнорировать
     * и не перебрасывать в сигнатуру
     */

    public SendUserIdRepository() {
        try {
            this.userIdsList = this.objectMapper.readValue(new File(Constants.JSON_SEND), new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
    }

    private void save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.JSON_SEND))) {
            objectMapper.writeValue(bufferedWriter, this.userIdsList);
        } catch (IOException e) {
        }
    }

    public void add(int id) {
        this.userIdsList.add(id);
        this.save();
    }


    public boolean contains(int id) {
        return this.userIdsList.contains(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendUserIdRepository that = (SendUserIdRepository) o;
        return Objects.equals(userIdsList, that.userIdsList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userIdsList);
    }

    @Override
    public String toString() {
        return "SendUserIdRepository{" +
                "userIdsList=" + userIdsList +
                '}';
    }
}
