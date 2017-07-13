package com.company;

import java.util.Comparator;

/**
 * Created by MWhite on 13/07/2017.
 */
final class Book {


    static int PublicKey = 0;
    int Key;
    int Quantity;
    String BookName;
    String AuthorName;

    public Book(int quantity, String bookName, String authorName) {
        PublicKey = PublicKey + 1;
        Key = PublicKey;
        Quantity = quantity;
        BookName = bookName;
        AuthorName = authorName;
    }

    public int GetKey() {
        return Key;
    }

    public int GetQuantity() {
        return Quantity;
    }

    public String GetBookName() {
        return BookName;
    }

    public String GetAuthorName() {
        return AuthorName;
    }

    public String toString() {
        return String.format("%-20s %-20s %-20s %s", GetKey(), GetBookName(), GetAuthorName(), GetQuantity());
    }



}



