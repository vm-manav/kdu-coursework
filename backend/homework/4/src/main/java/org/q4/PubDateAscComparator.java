package org.q4;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {

    @Override
    public int compare(Book firstBook, Book secondBook) {
        if(firstBook.getYear()<secondBook.getYear()){
            return -1;
        } else if (firstBook.getYear()>secondBook.getYear()) {
            return 1;
        } else {
            return firstBook.getTitle().compareTo(secondBook.getTitle());
        }
    }
}
