package com.example.springjpa.exceptions;

import com.example.springjpa.dto.ErrorDTO;
import com.example.springjpa.exceptions.customexceptions.NoShiftUserFoundWithGivenIdException;
import com.example.springjpa.exceptions.customexceptions.NoUserFoundWithGivenShiftTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandeller {
    @ExceptionHandler(value = {NoUserFoundWithGivenShiftTimeException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoUsersFoundWithGuvenTime(NoUserFoundWithGivenShiftTimeException ex){
        log.error("Cannot get any shift user working in given time");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NoShiftUserFoundWithGivenIdException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoShiftUsersFoundWithId(NoShiftUserFoundWithGivenIdException ex){
        log.error("Cannot get any shift user with given ID ID");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
