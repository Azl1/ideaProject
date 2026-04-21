package com.abdullaevaziz.factory;

import com.abdullaevaziz.model.LogicElement;
import com.abdullaevaziz.model.Xor;

public class XorFactory implements ElementFactoryI{
    @Override
    public LogicElement newInstance(int n) {
        return new Xor(n);
    }
}
