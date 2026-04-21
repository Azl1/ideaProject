package com.abdullaevaziz.model;

/**
 * Произвести создание производных классов
 * And, Or, Xor от класса LogicElement, реализуя метод operation как операции
 * И, ИЛИ, ИСКЛЮЧАЮЩЕЕ ИЛИ соответственно
 */

import com.abdullaevaziz.factory.ElementFactoryI;
import com.abdullaevaziz.factory.Factory;

/**
 * ИЛИ |
 */
public class Or extends LogicElement  {

    public Or() {
    }

    public Or(int n) {
        super(n);
    }

    @Override
    protected boolean operation(boolean a, boolean b) {
        return a || b;
    }

}
