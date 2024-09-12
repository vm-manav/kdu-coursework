package com.example.assignmenet.exceptions;

import com.example.assignmenet.dto.ErrorDTO;
import com.example.assignmenet.exceptions.customexceptions.BookAlreadyExistException;
import com.example.assignmenet.exceptions.customexceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandel {
    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleBookNotFoundException(BookNotFoundException exception){
        log.error("Book not found in the database");
        ErrorDTO error = new ErrorDTO(exception.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {BookAlreadyExistException.class})
    public ResponseEntity<ErrorDTO> handleBookExistException(BookAlreadyExistException exception){
        log.error("Book already exist in the database");
        ErrorDTO error = new ErrorDTO(exception.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleDataNotFoundException(HttpMessageNotReadableException exception){
        log.error("Data provided has some missing fields");
        ErrorDTO error = new ErrorDTO("Missing data in JSON objects",HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

