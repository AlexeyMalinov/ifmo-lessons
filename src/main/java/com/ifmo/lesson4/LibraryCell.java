package com.ifmo.lesson4;

public class LibraryCell {
    private final Book book;
    private int quantity;

    public LibraryCell(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }
}
