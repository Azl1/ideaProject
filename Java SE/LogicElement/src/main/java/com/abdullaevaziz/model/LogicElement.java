package com.abdullaevaziz.model;

import java.util.Arrays;

/**
 * Создайте базовый класс LogicElement в пакете model, описывающий понятие логического элемента,
 * имеющего n входов и один выход.
 * Полем данного класса является массив типа boolean, хранящий значения на каждом входе логического элемента.
 * Определите для него конструктор,
 * принимающий на вход целое число n и производящий инициализацию массива размером n.
 */
public abstract class LogicElement implements Comparable<LogicElement>{

    private boolean[] inputs;

    public LogicElement() {
    }

    public LogicElement(int n) {
        this.inputs = new boolean[n];
    }

    /**
     * Выполнить переопределение методов equals и hashCode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicElement that = (LogicElement) o;
        return Arrays.equals(inputs, that.inputs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(inputs);
    }

    /**
     * Реализовать метод toString таким образом, чтобы он выводил в начале название типа логического элемента,
     * далее значения на каждом из его входов и значение выхода в удобном формате для чтения
     */
    @Override
    public String toString() {
        return "LogicElement{" + this.getClass().getSimpleName() +
                " n=" + Arrays.toString(inputs) + "; " + this.result() + '}';
    }

    /**
     * Реализуйте метод fill с переменным числом аргументов типа boolean,
     * производящий копирование данных аргументов в поле-массив, используя метод System.arraycopy.
     * Если количество аргументов меньше длины исходного массива
     * необходимо сгенерировать исключение IllegalArgumentException с сообщением.
     */
    public void fill(boolean... values) {
        if (values.length < this.inputs.length) {
            throw new IllegalArgumentException("Некорректное аргумент метода");
        }
        System.arraycopy(values, 0, this.inputs, 0, this.inputs.length);
    }

    /**
     * Реализуйте метод getLength, возвращающий количество входов в логическом элементе
     */
    public int getLength() {
        return this.inputs.length;
    }

    /**
     * Объявите абстрактный protected метод operation,
     * принимающий на вход два аргумента типа boolean,
     * который будет возвращать результат логической операции,
     * определенной в классе-наследнике для LogicElement
     */
    abstract protected boolean operation(boolean a, boolean b);


    /**
     * Реализовать метод result,
     * производящий вычисление значения на выходе логического элемента,
     * используя n его входов и метод operation
     */
    public boolean result() {
        boolean res = this.inputs[0];
        for (int i = 1; i < this.inputs.length; i++) {
            res = this.operation(res, this.inputs[i]);
        }
        return res;
    }

    @Override
    public int compareTo(LogicElement o) {
       return Integer.compare(this.getLength(), o.getLength());
    }
}
