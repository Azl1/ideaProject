package com.abdullaevaziz.repository;

import com.abdullaevaziz.factory.ElementFactoryI;
import com.abdullaevaziz.factory.Factory;
import com.abdullaevaziz.factory.LogicElementType;
import com.abdullaevaziz.model.And;
import com.abdullaevaziz.model.LogicElement;
import com.abdullaevaziz.model.Or;
import com.abdullaevaziz.model.Xor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class ElementRepository  {

    /**
     * 1. Создайте класс ElementRepository в пакете repository.
     * В качестве поля класса выступает список логических элементов.
     * Конструктор класса принимает на вход имя файла csv,
     * где располагаются записи логических элементов,
     * а так же Map<String, ElementFactoryI> map, где map – словарь,
     * хранящий названия типов логических элементов и ассоциированные с ними фабрики,
     * String – название типа логического элемента,
     * а ElementFactoryI – реализация интерфейса фабрики для этого элемента
     */

    /**
     * 2. Создайте файл .csv, имеющий следующий формат представления данных:
     * название_типа_логического_элемента; значения_логических_элементов_через«;»
     * Например:
     * AND;true;true;false;false
     * Количество значений может быть для каждого элемента разным
     */
    private ArrayList<LogicElement> logicElementsList = new ArrayList<>();

    /**
     * 3. В конструкторе класса произвести загрузку логических элементов
     * из .csv файла в поле класса. При чтении и заполнении данными списка
     * использовать рациональные алгоритмы,
     * а так же весь реализованный функционал ранее.
     * При реализации пользоваться аргументом-словарем фабрик
     */

    /**
     * 4. Произвести корректную обработку всех возможных возникающих ошибок,
     * некорректные данные при чтении пропускать
     */
    public ElementRepository(String fileName, Map<String, ElementFactoryI> map) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            try {
                String[] split = strLine.split(";");
                ElementFactoryI factoryI = map.getOrDefault(split[0], null);

                LogicElement logicElement = factoryI.newInstance(split.length - 1);
                boolean[] mass = new boolean[split.length - 1];
                for (int i = 0; i < mass.length; i++) {
                    mass[i] = Boolean.parseBoolean(split[i + 1]);
                }
                logicElement.fill(mass);
                this.logicElementsList.add(logicElement);


            } catch (RuntimeException ignored) {
            }
        }
    }

    /**
     * 5. Написать перегрузку конструктора, который принимает на вход имя файла csv,
     * где располагаются записи логических элементов,
     * и производит заполнение списка логических элементов,
     * не используя словарь фабрик, а используя простую фабрику
     */
    public ElementRepository(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            try {
                String[] split = strLine.split(";");
                LogicElement logicElement = Factory.newInstance(LogicElementType.valueOf(split[0]), split.length - 1);
                boolean[] mass = new boolean[split.length - 1];
                for (int i = 0; i < mass.length; i++) {
                    mass[i] = Boolean.parseBoolean(split[i + 1]);
                }

                logicElement.fill(mass);
                this.logicElementsList.add(logicElement);

            } catch (RuntimeException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    /**
     * 6. Сравнить две реализации одного и того же функционала в разных конструкторах,
     * дать грамотную оценку преимуществ и недостатков между ними,
     * указать какая реализация лучше и почему
     */
    /**
     * 7. Реализовать метод toString, выводящий данные из списка,
     * каждый логический элемент должен выводиться с новой строки
     */
    @Override
    public String toString() {
        return "ElementRepository{" +
                "logicElementsList=" + logicElementsList + "\n" +
                '}';
    }

    /**
     * 1. В классе-репозитории реализовать метод sort, который принимает на вход реализацию интерфейса Comparator
     * и производит сортировку списка данным объектом,
     * если он не null, или же методом compareTo в противном случае
     */
    public void sort(Comparator<LogicElement> comparator) {
        this.logicElementsList.sort(comparator);
    }
}
