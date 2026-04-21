package com.abdullaevaziz.factory;

import com.abdullaevaziz.model.And;
import com.abdullaevaziz.model.LogicElement;

public class AndFactory implements ElementFactoryI{

    @Override
    public LogicElement newInstance(int n) {
        return new And(n);
    }
}
