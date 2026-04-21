package com.abdullaevaziz.factory;

import com.abdullaevaziz.model.And;
import com.abdullaevaziz.model.LogicElement;
import com.abdullaevaziz.model.Or;
import com.abdullaevaziz.model.Xor;

/**
 * Задания на реализации фабрик
 * Фабрикой называется класс, содержащий фабричный метод,
 * производящий создание новых объектов других классов по заданному механизму.
 * Тип возвращаемого значения данного метода имеет ссылку на базовый класс создаваемых объектов.
 */
public class Factory {

    public Factory() {
    }

    /**
     * 1. Реализовать в пакете factory простую фабрику объектов со статическим методом newInstance,
     * принимающем на вход экземпляр перечисления (enum: AND, OR, XOR) и
     * количество входов логического элемента.
     * В зависимости от переданного значения enum вернуть объект требуемого класса.
     * Если передан неизвестный экземпляр enum, то вернуть значение null.
     */


    public static LogicElement newInstance(LogicElementType logicElementType, int n){
        return switch (logicElementType){
            case AND -> new And(n);
            case OR -> new Or(n);
            case XOR -> new Xor(n);
        };
    }



}
