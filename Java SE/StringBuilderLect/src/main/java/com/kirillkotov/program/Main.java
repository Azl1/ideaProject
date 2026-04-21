package com.kirillkotov.program;

public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder("Hello World!");

        System.out.println("Print StringBuilder1");
        System.out.println(stringBuilder1);

        System.out.println("\nPrint StringBuilder2");
        System.out.println(stringBuilder2);

        String str1 = stringBuilder1.toString();
        String str2 = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder1");
        System.out.println(str1);
        System.out.println("\nPrint string from StringBuilder2");
        System.out.println(str2);

        stringBuilder2.append(10).append(" ").append(12.5);
        String strAppend = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after append");
        System.out.println(strAppend);

        stringBuilder2.delete(1, 5);
        String strDelete = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after delete substring");
        System.out.println(strDelete);

        stringBuilder2.deleteCharAt(1);
        String strDeleteCharAt = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after delete substring");
        System.out.println(strDeleteCharAt);

        stringBuilder2.insert(5, "~~~~~~~");
        String strInsert = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after insert substring");
        System.out.println(strInsert);

        stringBuilder2.replace(1, 10, "EEEE");
        String strReplace = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after insert substring");
        System.out.println(strReplace);

        stringBuilder2.reverse();
        String strReverse = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after reverse");
        System.out.println(strReverse);

        stringBuilder2.setCharAt(10, '*');
        String strReplaceChar = stringBuilder2.toString();
        System.out.println("\nPrint string from StringBuilder2 after replace symbol");
        System.out.println(strReplaceChar);

        String string = new StringBuilder("Hello World").append("HHH").append(" ")
                .append(10).delete(1, 5).insert(1, "dsds").toString();
        System.out.println("\nPrint string");
        System.out.println(string);
    }
}