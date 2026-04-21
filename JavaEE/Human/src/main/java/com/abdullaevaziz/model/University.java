package com.abdullaevaziz.model;


import lombok.*;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class University {
    @NonNull
    private String name;
    @NonNull
    private String address;

    @Setter(AccessLevel. NONE)
    @NonNull
    private ArrayList<Human> humanList = new ArrayList<>();

    public void addHuman(Human human) {
        this.humanList.add(human);
    }


    public int maxSalary() {
        return this.humanList.stream().mapToInt(Human::getSalary).max().orElse(0);
    }

    //TODO найти всех людей с максимальныой зп
    public ArrayList<Human> maxSalaryAll() {
        ArrayList<Human> res = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Human human : this.humanList) {
            if (human.getSalary() > max) {
                max = human.getSalary();
            }
        }

        for (Human human : this.humanList) {
            if (human.getSalary() == max) {
                res.add(human);
            }
        }
        return res;
    }

    //TODO сделать метод удаления человека
    public boolean remove(Human human) {
        return this.humanList.remove(human);
    }

    //TODO сделать метод обновления человека
    public ArrayList<Human> updateHuman( Human updatedHuman) {
        for (Human human : this.humanList) {
            if(human.equals(updatedHuman)) {
                human.setName(updatedHuman.getName());
                human.setAge(updatedHuman.getAge());
                human.setSalary(updatedHuman.getSalary());
                human.setWeight(updatedHuman.getWeight());
                this.humanList.add(human);
                break;
            }
        }
        return this.humanList;
    }

    //TODO метод получения человека по имени
    public Human humanGet(String humanName){
        for (Human human : this.humanList) {
            if(human.getName().equals(humanName)){
                return  human;
            }
        }
        return null;
    }

    //TODO получение вскех людей из универа
    public ArrayList<Human> humanGetAll(){
        return this.humanList;
    }
}
