package com.abdullaevaziz.factory;

import com.abdullaevaziz.model.LogicElement;
import com.abdullaevaziz.model.Or;

public class OrFactory implements ElementFactoryI{
    @Override
    public LogicElement newInstance(int n) {
        return new Or(n);
    }
}
