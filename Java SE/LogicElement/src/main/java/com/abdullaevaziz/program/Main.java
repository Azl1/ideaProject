package com.abdullaevaziz.program;

import com.abdullaevaziz.factory.AndFactory;
import com.abdullaevaziz.factory.ElementFactoryI;
import com.abdullaevaziz.factory.OrFactory;
import com.abdullaevaziz.factory.XorFactory;
import com.abdullaevaziz.model.And;
import com.abdullaevaziz.model.LogicElement;
import com.abdullaevaziz.model.Or;
import com.abdullaevaziz.model.Xor;
import com.abdullaevaziz.repository.ElementRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*And and1 = new And(4);
        And and2 = new And(3);

        Or or1 = new Or(4);
        Or or2 = new Or(4);

        Xor xor1 = new Xor(2);
        Xor xor2 = new Xor(2);

        and1.fill(true, true, false, true, true, true, true, true, true, true);
        and2.fill(false, false, true);

        or1.fill(true, true, true, true);
        or2.fill(false, false, false, true);

        xor1.fill(true, false);
        xor2.fill(false, true);

        System.out.println(and1);
        System.out.println(and2);

        System.out.println(or1);
        System.out.println(or2);

        System.out.println(xor1);
        System.out.println(xor1);*/

        try {
            Map<String, ElementFactoryI> map = new HashMap<>();
            map.put("AND",new AndFactory());
            map.put("OR",new OrFactory());
            map.put("XOR", new XorFactory());
            System.out.println("elementRepository1----------------------------------------");
            ElementRepository elementRepository1 = new ElementRepository("elementFactory.csv", map);
            System.out.println(elementRepository1);

            System.out.println("elementRepository2----------------------------------------");
            ElementRepository elementRepository2 = new ElementRepository("elementFactory.csv");
            System.out.println(elementRepository2);


            elementRepository1.sort(null);
            System.out.println("ElementRepository1 sort: " + "\n");
            System.out.println(elementRepository1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}