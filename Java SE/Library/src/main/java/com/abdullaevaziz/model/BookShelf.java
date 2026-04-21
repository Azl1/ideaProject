package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Книжная полка
 */
public class BookShelf  {

    private int number;
    ArrayList<Book> lisBook = new ArrayList<>();

    public BookShelf() {
    }

    public BookShelf(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookShelf bookShelf = (BookShelf) o;
        return number == bookShelf.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "number=" + number +
                '}';
    }

    /**
     * 4. Написать методы add в классе BookShelf
     * для добавления данных в списки
     */
    public void add(Book book){
        this.lisBook.add(book);
    }

    /**
     * метод поиска книги по объекту
     * @param book
     * @return
     */
    public int search(Book book){
        return lisBook.indexOf(book);
    }


    public Book search(String name) {
        for (Book book : this.lisBook) {
            if(book.getName().equals(name)){
                return book;
            }
        }
        return null;
    }


    /**
     * метод,
     * который возвращает список книг заданного автора
     */
    public ArrayList<Book> searchList(String author) {
        ArrayList<Book> listBookAuthor = new ArrayList<>();
        for (Book book : lisBook) {
          if (book.getAuthor().equals(author)) {
              listBookAuthor.add(book);
          }
        }
        return listBookAuthor;
    }

    /**
     * метод удаления элементов,
     * которые хранят списки этих классов
     */
    public boolean remove(Book book){
        return lisBook.remove(book);
    }


}
