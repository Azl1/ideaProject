package com.kirillkotov.program;

import com.kirillkotov.model.Size;
import com.kirillkotov.model.TV;

public class Main {
    public static void main(String[] args) {
        Size size1 = new Size(1,2,3);
        System.out.println("Print original size");
        System.out.println(size1);

        Size sizeCopy1 = new Size(size1);
        System.out.println("Print CopySize1 using CopyConstructor");
        System.out.println(sizeCopy1);

        Size sizeCopy2 = size1.clone();
        System.out.println("Print CopySize1 using clone");
        System.out.println(sizeCopy2);

        TV tv = new TV("Samsung", "1300", "Black", 10, 35000, size1);
        System.out.println("\nPrint original tv");
        System.out.println(tv);
        TV tv1 = new TV(tv);
        System.out.println("Print tv1 using CopyConstructor");
        System.out.println(tv1);

        TV tv2 = tv.clone();
        System.out.println("Print tv2 using clone");
        System.out.println(tv2);
    }
}
