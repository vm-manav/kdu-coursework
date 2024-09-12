package com.example.assignmenet.service;

import com.example.assignmenet.dto.BookRequestDTO;
import com.example.assignmenet.dto.BookResponseDTO;
import com.example.assignmenet.module.Book;
import com.example.assignmenet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }
    public BookResponseDTO getBook(int bookId) {
        Book book=bookRepository.getBookFromDatabase(bookId);
        return convertEntityToResponseDTO(book);

    }

    public void deleteBook(int bookId) {
        bookRepository.deleteBookFromDatabase(bookId);
    }

    public void insertBook(BookRequestDTO bookRequestDTO) {
        Book book=convertToBookFromRequestDTO(bookRequestDTO);
        bookRepository.addBookToDatabase(book);
    }
    public void updateBook(BookRequestDTO bookRequestDTO) {
        Book book=convertToBookFromRequestDTO(bookRequestDTO);
        bookRepository.updateBookToDatabase(book);
    }

    public BookResponseDTO convertEntityToResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setBookAuthor(book.getBookAuthor());
        bookResponseDTO.setBookName(book.getBookName());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setQuantity(book.getQuantity());
        return bookResponseDTO;
    }

    public Book convertToBookFromRequestDTO(BookRequestDTO bookRequestDTO) {
        Book book=new Book();
        book.setBookId(bookRequestDTO.getBookId());
        book.setBookAuthor(bookRequestDTO.getBookAuthor());
        book.setBookName(bookRequestDTO.getBookName());
        book.setQuantity(bookRequestDTO.getQuantity());
        book.setPrice(bookRequestDTO.getPrice());
        return book;
    }
}
