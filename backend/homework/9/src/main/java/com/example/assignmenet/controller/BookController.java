package com.example.assignmenet.controller;

import com.example.assignmenet.dto.BookRequestDTO;
import com.example.assignmenet.dto.BookResponseDTO;
import com.example.assignmenet.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService=bookService;
    }

    @PostMapping("")
    public ResponseEntity<String> addBook(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        bookService.insertBook(bookRequestDTO);
        return ResponseEntity.ok("Book added successfully!");
    }

    @GetMapping("")
    public BookResponseDTO getBook(@RequestParam int bookId)
    {
        return bookService.getBook(bookId);
    }

    @PutMapping("")
    public ResponseEntity<String> updateBook(@RequestBody @Valid BookRequestDTO bookRequestDTO)
    {
        bookService.updateBook(bookRequestDTO);
        return ResponseEntity.ok("Book updated successfully!");
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteBook(@RequestParam int bookId)
    {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book removed successfully");
    }
}
