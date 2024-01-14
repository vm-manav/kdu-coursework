package org.q4;

import org.Logger;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {

    public static void treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(comparator);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            Logger.infoMessage("Book [title=".concat(book.getTitle()).concat(", author=").concat(book.getAuthor()).concat(", year=").concat(String.valueOf(book.getYear())).concat("]"));
        }

    }

    public static void main(String[] args) {
        treeSetDemo(new PubDateDescComparator());
    }

}