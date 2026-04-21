package com.abdullaevaziz.program;

import com.abdullaevaziz.model.Book;
import com.abdullaevaziz.model.BookShelf;
import com.abdullaevaziz.model.Library;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Book book = new Book();
        Book book1 = new Book("Atomic Heart", "Хорф Харальд", 2023);
        Book book2 = new Book("Жизнь Взаймы, Или У Неба любимчиков Нет", "Ремарк Эрих Мария", 2018);
        Book book3 = new Book("Десять негритят", "Кристи Агата", 2019);
        Book book4 = new Book("Триггер. Как далеко ты можешь зайти?", "Воронин Петр", 2020);
        Book book5 = new Book("Крысиный остров и другие истории", "Несбё Ю", 2021);

        BookShelf bookShelf = new BookShelf();
        BookShelf bookShelf1 = new BookShelf(1);
        BookShelf bookShelf2 = new BookShelf(2);

        Library library1 = new Library("Крупского", "пр. Королева д.25");

        bookShelf1.add(book1);
        bookShelf1.add(book2);
        bookShelf1.add(book3);
        bookShelf1.add(book4);
        bookShelf1.add(book5);

        System.out.println();
        System.out.println(book1);
        System.out.println(bookShelf1);

        int search = bookShelf1.search(book5);
        System.out.println(search);

        System.out.println();
        ArrayList<Book> bookArrayList = bookShelf1.searchList("Хорф Харальд");
        System.out.println(bookArrayList);

        System.out.println();
        boolean res = bookShelf1.remove(book2);
        System.out.println(res);

        System.out.println("-----------------------------------------------------------------------------------");

        System.out.println();
        library1.add(bookShelf1);
        library1.add(bookShelf);
        BookShelf bookShelfLibrary = library1.search(1);
        System.out.println(bookShelfLibrary);
        int searchLibrary = library1.search(bookShelf1);
        System.out.println(searchLibrary);
        Book book6 = library1.search("Atomic Heart");
        System.out.println(book6);
        int[] index = library1.search(book1);
        System.out.println(Arrays.toString(index));
        boolean removeLibrary = library1.remove(bookShelf1);
        System.out.println(removeLibrary);
    }
}