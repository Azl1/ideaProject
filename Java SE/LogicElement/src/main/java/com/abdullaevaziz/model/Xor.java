package com.abdullaevaziz.model;
/**
 * Произвести создание производных классов
 * And, Or, Xor от класса LogicElement, реализуя метод operation как операции
 * И, ИЛИ, ИСКЛЮЧАЮЩЕЕ ИЛИ соответственно
 */

import com.abdullaevaziz.factory.ElementFactoryI;
import com.abdullaevaziz.factory.Factory;

/**
 * Xor ^
 */
public class Xor extends LogicElement {

    public Xor() {
    }

    public Xor(int n) {
        super(n);
    }

    @Override
    protected boolean operation(boolean a, boolean b) {
        return a ^ b;
    }

}
