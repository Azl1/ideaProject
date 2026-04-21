package com.abdullaevaziz.repository;

import com.abdullaevaziz.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.abdullaevaziz.model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryPerson {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Person> personList = new ArrayList<>();

    public RepositoryPerson() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.PERSON_FILE))) {
            this.personList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
    }

    public void savePerson() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.PERSON_FILE))) {
            this.personList = objectMapper.readValue(bufferedReader, new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
    }

    public boolean add(Person person) throws IOException {
        person.setId(this.personList.stream().mapToInt(Person::getId).max().orElse(0) + 1);
        this.personList.add(person);
        savePerson();
        return true;
    }

    public Person get(int id) {
        return this.personList.stream().filter(personId -> personId.getId() == id)
                .findFirst().orElse(null);
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public boolean updatePersons(Person newPerson) throws IOException {
        for (int i = 0; i < this.personList.size(); i++) {
            if (this.personList.get(i) == newPerson) {
                this.personList.add(i, newPerson);
                savePerson();
                return true;
            }
        }
        return false;
    }

    public boolean deletePerson(int id) {
        return this.personList.removeIf(person -> person.getId() == id);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "personList=" + personList +
                '}';
    }
}
