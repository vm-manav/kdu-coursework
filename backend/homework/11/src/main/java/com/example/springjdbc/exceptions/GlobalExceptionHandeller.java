package com.example.springjdbc.exceptions;

import com.example.springjdbc.dto.ErrorDTO;
import com.example.springjdbc.exceptions.customexceptions.NoValueExistInTableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandeller {
    @ExceptionHandler(value = {NoValueExistInTableException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoUsersFound(NoValueExistInTableException ex){
        log.error("Cannot get the Id in table");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
