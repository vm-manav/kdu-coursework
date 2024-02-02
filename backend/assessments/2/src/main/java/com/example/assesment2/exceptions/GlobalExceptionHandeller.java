package com.example.assesment2.exceptions;

import com.example.assesment2.dto.ErrorDTO;
import com.example.assesment2.exceptions.customexceptions.NoEntityFoundWithGivenIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandeller {

    @ExceptionHandler(value = {NoEntityFoundWithGivenIdException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoShiftUsersFoundWithId(NoEntityFoundWithGivenIdException ex){
        log.error("Cannot get any shift user with given ID ID");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
