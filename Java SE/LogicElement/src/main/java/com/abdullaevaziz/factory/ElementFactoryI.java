package com.abdullaevaziz.factory;

import com.abdullaevaziz.model.LogicElement;

public interface ElementFactoryI {

    /**
     * 2. Реализовать более сложный вариант фабрики посредством создания интерфейса ElementFactoryI
     * с методом newInstance, принимающем в качестве аргументов количество входов логического элемента
     * и возвращающий LogicElement. Далее необходимо для каждого типа логического элемента
     * создать классы фабрик с реализацией данного интерфейса.
     */
    LogicElement newInstance(int n);
}
