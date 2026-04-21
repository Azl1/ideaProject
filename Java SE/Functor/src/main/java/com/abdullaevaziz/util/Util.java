package com.abdullaevaziz.util;

import java.lang.reflect.Array;
import java.util.function.Function;

public class Util{


    /**
     * Напишите метод filter в классе Util пакета util,
     * который принимает на вход массив (любого типа) и реализацию интерфейса
     * Filter(создать самим) c методом boolean apply(T o), возвращает новый массив,
     * убрав из исходного массива лишние элементы, не удовлетворяющие фильтру.
     * Метод apply возвращает истину, если входящий аргумент удовлетворяет фильтру,
     * и ложь в противном случае.
     * При реализации использовать шаблоны интерфейсов и методов
     */
    public static <T> T[] filter(T[] mass, Filter<T> filter) {
        int count = 0;
        for (T t : mass) {
            if (filter.apply(t)) {
                count++;
            }
        }

        T[] res = (T[]) Array.newInstance(mass.getClass().componentType(), count);
        int j = 0;
        for (T t : mass) {
            if (filter.apply(t)) {
                res[j] = t;
                j++;

            }
        }
        return res;
    }

    /**
     * Напишите метод filter после выполнения всех задач ниже,
     * который принимает на вход массив (любого типа) и реализацию стандартного интерфейса
     * Function<T, R>, где T – тип входного значения метода apply,
     * а R – тип возвращаемого значения, которое должно быть указано явно Boolean
     */
    public static <T> T[] filter1(T[] mass, Function<T, Boolean> function){
        int count = 0;
        for (T t : mass) {
            if(function.apply(t)){
                count++;
            }
        }

        T[] res = (T[]) Array.newInstance(mass.getClass().componentType(), count);
        int j = 0;
        for (T reT : mass) {
            if(function.apply(reT)){
                res[j] = reT;
                j++;
            }
        }
        return res;
    }


}
