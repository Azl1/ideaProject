package com.abdullaevaziz.model;

import java.util.Objects;

/**
 * Книга
 */
public class Book {

    private String name;
    private String author;
    private int bookReleaseDate;

    public Book() {
    }

    public Book(String name, String author, int bookReleaseDate) {
        this.name = name;
        this.author = author;
        this.bookReleaseDate = bookReleaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(int bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookReleaseDate == book.bookReleaseDate
                && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, bookReleaseDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", bookReleaseDate=" + bookReleaseDate +
                '}';
    }


}
