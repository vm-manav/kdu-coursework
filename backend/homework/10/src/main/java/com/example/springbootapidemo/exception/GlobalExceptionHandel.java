package com.example.springbootapidemo.exception;

import com.example.springbootapidemo.dto.ErrorDTO;
import com.example.springbootapidemo.exception.customexceptions.NoUsersFoundException;
import com.example.springbootapidemo.exception.customexceptions.UserAlreadyExistException;
import com.example.springbootapidemo.exception.customexceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler class using ControllerAdvice for handling custom exceptions in a Spring Boot application.
 * Provides specific handling for NoUsersFoundException, UserAlreadyExistException, and UserNotFoundException.
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandel {

    @ExceptionHandler(value = {NoUsersFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoUsersFound(NoUsersFoundException ex){
        log.error("No users present in the list");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionUserAlreadyExist(UserAlreadyExistException ex){
        log.error("User already exist in the list");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionUserNOtFound(UserNotFoundException ex){
        log.error("No such user found with given username");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
