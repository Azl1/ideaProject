package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * C) Создайте класс Garden,
 * который имеет в качестве поля массив растений.
 * Конструктор - только с параметром,
 * производящий инициализацию массива размером аргумента n
 */
public class Garden implements Cloneable {
    private List<Plant> plantsList;

    public Garden(int n) {
        this.plantsList = new ArrayList<>(n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garden garden = (Garden) o;
        return Objects.equals(plantsList, garden.plantsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantsList);
    }

    /**
     * 1. add - аргумент объект типа Plant,
     * который добавляет цветок в массив растений,
     * возвращает true или false - получилось ли добавить
     * цветок в массив или нет.
     */
    public boolean add(Plant plant) {
        return this.plantsList.add(plant);
    }

    /**
     * 2. get на вход принимает индекс растения,
     * возвращает растение из массива растений
     * по его индексу или null если индекс некорректный.
     */
    public Plant get(int index) {
        return this.plantsList.get(index);
    }

    /**
     * 3. count - возвращает реальное количество растений в саду
     * (столько сколько добавлено в массив, а не его размер).
     */
    public int count() {
        return plantsList.size();
    }

    /**
     * 4. Метод строкового представления - возвращает строку из всех растений в саду.
     */
    @Override
    public String toString() {
        return "Garden{" +
                "plantsList=" + plantsList +
                '}';
    }

    /**
     * 5. search на вход принимает имя растения
     * и возвращает найденный объект по его имени
     * или null, если такой объект не найден
     */
    public Plant search(String namePlant) {
        for (Plant plant : this.plantsList) {
            if (plant.getName().equals(namePlant)) {
                return plant;
            }
        }
        return null;
    }

    /**
     * 6. search на вход принимает объект растения
     * и возвращает его индекс в списке всех
     * растений в саду или -1, если такой объект не найден
     */
    public int search(Plant plant) {
        return this.plantsList.indexOf(plant);
    }

    /**
     * 7. delete принимает на вход имя растения и
     * производит удаление растения по его имени из сада,
     * сдвигая все растения после удаленного влево,
     * возвращает удаленный объект растения
     */
    public Plant delete(String plantName) {
        Plant plantRemove = search(plantName);
        this.plantsList.remove(plantRemove);
        return plantRemove;
    }

    /**
     * 8. delete принимает на вход объект растения и производит удаление данного растения из сада, сдвигая все растения после удаленного влево,
     * возвращает логическое значение, получилось или нет удалить растение.
     */
    public boolean delete(Plant plant) {
        return plantsList.remove(plant);
    }

    /**
     * 9. insert принимает на вход индекс и объект типа Plant
     * и производит вставку этого объекта по заданному индексу,
     * сдвигая все имеющиеся объекты вправо,
     * возвращает логическое значение,
     * получилось или нет вставить растение
     */
    public boolean insert(int index, Plant plant) {
        if (index >= 0 || index <= plantsList.size()) {
            this.plantsList.add(index, plant);
            return true;
        }
        return false;
    }


    @Override
    public Garden clone() {
        try {
            Garden clone = (Garden) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            //TODO скопировать арлист
            clone.plantsList = new ArrayList<>();

            //TODO циклом склонировать растения внутри арлисте
            for (Plant plant : this.plantsList) {
                clone.plantsList.add(plant.clone());
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
