package com.company;


import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.text.Collator;


public class Main {
    static Scanner userInput = new Scanner(System.in);
    static ArrayList<Book> BookList = new ArrayList<Book>();
    static String[] MenuControls = new String[]{"Add Book", "Remove Book", "Sort List", "List Books", "Quit"};

    public static void main(String[] args) {

        //Sample Data
        BookList.add(new Book(13, "Aercie Jackson", "Deter Jackson"));
        BookList.add(new Book(12, "Fkull Fire", "Childish Author"));
        BookList.add(new Book(11, "Berfect Dream", "Feter Anderson"));
        BookList.add(new Book(9, "Eerfect Dream", "Geter Anderson"));
        BookList.add(new Book(2, "Derfect Dream", "Heter Anderson"));
        BookList.add(new Book(8, "Cerfect Dream", "Aeter Anderson"));

        int num = -1;
        boolean quit = false;


        while (!quit) {
            num = -1;


            for (int i = 0; i < MenuControls.length; i++) {

                System.out.println(String.format("%s: %s", i, MenuControls[i].toString()));

            }
            String input = userInput.next().trim();
            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                num = -1;
            }
            if (num == 0 || input.matches("(?i).*add*")) {
                AddBook();
            } else if (num == 1 || input.matches("(?i).*remove*")) {
                RemoveRecord();
            } else if (num == 2 || input.matches("(?i).*sort*")) {
                SortList();
            } else if (num == 3 || input.matches("(?i).*list*")) {
                DisplayBooks();
            } else if (num == 4 || input.matches("(?i).*quit*")) {
                quit = true;
            }

        }
    }

    public static void AddBook() {
        boolean valid;
        String name;

        do {

            System.out.println("Book Name:");
            name = userInput.next().trim();
            if (name.length() < 1 || name.contains("  ")) {
                System.out.println("Please enter a valid book title");
                valid = false;
            } else {
                valid = true;
            }

        } while (!valid);

        String author;

        do {
            System.out.println("Author:");
            author = userInput.next().trim();
            if (author.length() < 1 || author.contains("  ")) {
                System.out.println("Please enter a valid author");
                valid = false;

            } else {
                valid = true;
            }
        } while (!valid);
        int bookQuantity = 1;

        do {

            System.out.println("Quantity:");
            String quantity = userInput.next().trim();

            try {
                bookQuantity = Integer.parseInt(quantity);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number");
                valid = false;
            }

        } while (!valid);
        BookList.add(new Book(bookQuantity, name, author));

    }

    public static void DisplayBooks() {
        System.out.println(String.format("%-20s %-20s %-20s %s", "Key", "Title", "Author", "Quantity"));
        for (Book item : BookList) {
            System.out.println(item.toString());

        }
        System.out.println();

    }

    public static void RemoveRecord() {

        boolean valid;
        System.out.print("Key: ");
        String input = userInput.next().trim();
        int key = 0;
        do {
            try {
                key = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number");
                valid = false;
            }
        } while (!valid);

        for (Book item : BookList) {
            if (item.GetKey() == key) {
                BookList.remove(item);
                return;
            }

        }
        System.out.println("Invalid Key");

    }

    public static void SortList() {
        int num;

        System.out.println(String.format("0: Title\n1: Author\n2: Quantity"));
        String input = userInput.next().trim();
        try
        {
            num = Integer.parseInt(input);
        }
        catch (NumberFormatException ex)
        {
            num = -1;
        }
        if (num == 0 || input.matches("(?i).*title*"))
        {
            Collections.sort(BookList, new CompareTitle());
        }
        else if (num == 1 || input.matches("(?i).*author*"))
        {
            Collections.sort(BookList, new CompareAuthor());
        }
        else if (num == 2 || input.matches("(?i).*quantity*"))
        {
            Collections.sort(BookList, new CompareQuantity());
        }
        else
        {
            Collections.sort(BookList, new CompareKey());
        }


    }

    static class CompareTitle implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return  book1.GetBookName().compareTo(book2.GetBookName());
        }
    }

    static class CompareAuthor implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.GetAuthorName().compareTo(book2.GetAuthorName());
        }
    }

    static class CompareQuantity implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return Integer.compare(book1.GetQuantity(), book2.GetQuantity());
        }
    }

    static class CompareKey implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return Integer.compare(book1.GetKey(), book2.GetKey());
        }

    }
}

