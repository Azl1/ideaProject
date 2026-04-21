package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Библиотека
 */
public class Library {

    private String name;

    private String address;

    private ArrayList<BookShelf> listBookShelf = new ArrayList<>();

    public Library() {
    }

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(name, library.name) && Objects.equals(address, library.address) && Objects.equals(listBookShelf, library.listBookShelf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, listBookShelf);
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * 4. Написать методы add в классе
     * Library для добавления данных в списки
     */

    public void add(BookShelf bookShelf) {
        listBookShelf.add(bookShelf);
    }

    /**
     * метод поиска книжной полки по номеру
     */
    public BookShelf search(int number) {
        for (BookShelf bookShelf : listBookShelf) {
            if (bookShelf.getNumber() == number) {
                return bookShelf;
            }
        }
        return null;
    }

    /**
     * метод поиска книжной полки по объекту
     */
    public int search(BookShelf bookShelf) {
        return listBookShelf.indexOf(bookShelf);
    }

    /**
     * поиск книги по имени (возвращает объект)
     */
    public Book search(String name) {
        for (BookShelf bookShelf : listBookShelf) {
            Book search = bookShelf.search(name);
            if (search != null) {
                return search;
            }
        }
        return null;
    }

    /**
     * поиск книги отдельно по объекту
     * (возвращает массив из 2-х индексов)
     */
    public int[] search(Book book) {
        for (int i = 0; i < listBookShelf.size(); i++) {
            BookShelf bookShelf = this.listBookShelf.get(i);
            int ind = bookShelf.search(book);
            if (ind != -1) {
                return new int[]{i, ind};
            }
        }
        return new int[0];
    }

    /**
     * метод удаления элементов,
     * которые хранят списки этих классов
     */
    public boolean remove(BookShelf bookShelf) {
        return listBookShelf.remove(bookShelf);
    }

}
