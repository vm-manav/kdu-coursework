package com.example.assignmenet.repository;

import com.example.assignmenet.exceptions.customexceptions.BookAlreadyExistException;
import com.example.assignmenet.exceptions.customexceptions.BookNotFoundException;
import com.example.assignmenet.module.Book;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Repository
public class BookRepository {

    private ArrayList<Book> bookList;
    @PostConstruct
    public void initialize(){
        bookList=new ArrayList<>();
        log.info("Database array initialised");
    }
    public void addBookToDatabase(Book book) {
        if(bookList.contains(book)) {
            throw new BookAlreadyExistException("Book can not be added .. book already exist");
        }
        log.info("Book Successfully added to the database");
        bookList.add(book);

    }
    public void updateBookToDatabase(Book book) {
        for(Book currentBook:bookList) {
            if(Objects.equals(currentBook.getBookId(), book.getBookId())) {
                currentBook.setBookName(book.getBookName());
                currentBook.setBookAuthor(book.getBookAuthor());
                currentBook.setPrice(book.getPrice());
                currentBook.setQuantity(book.getQuantity());
                log.info("Book updated in the database");
                return;
            }
        }
        throw new BookNotFoundException("Book ID not found .. Book cannot be Updated");
    }
    public void deleteBookFromDatabase(int bookId) {
        for(Book currentBook:bookList) {
            if(currentBook.getBookId()==bookId) {
                bookList.remove(currentBook);
                log.info("Book Successfully deleted from the database");
                return;
            }
        }
        throw new BookNotFoundException("Book ID not found .. Book cannot be Deleted");
    }
    public Book getBookFromDatabase(int bookId) {
        for(Book currentBook:bookList) {
            if(currentBook.getBookId()==bookId) {
                log.info("Book retrieved from the database");
                return currentBook;
            }
        }
        throw new BookNotFoundException("Book ID not found .. Book cannot be Fetched");
    }
}