package com.kdu.caching.exceptions;

import com.kdu.caching.dto.ErrorDTO;
import com.kdu.caching.exceptions.customexceptions.AddressNotExistException;
import com.kdu.caching.exceptions.customexceptions.InvalidLocationPointException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandel {

    /**
     * Handles exceptions of type InvalidLocationPointException.
     * @param exception The InvalidLocationPointException to be handled.
     * @return ResponseEntity with an ErrorDTO containing the error message and HTTP status code.
     */
    @ExceptionHandler(value = {InvalidLocationPointException.class})
    public ResponseEntity<ErrorDTO> handleInvalidLocationPointException(InvalidLocationPointException exception){
        log.error("Location coordinates provided are not valid");
        ErrorDTO error = new ErrorDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions of type AddressNotExistException.
     * @param exception The AddressNotExistException to be handled.
     * @return ResponseEntity with an ErrorDTO containing the error message and HTTP status code.
     */
    @ExceptionHandler(value = {AddressNotExistException.class})
    public ResponseEntity<ErrorDTO> handleAddressNotFoundException(AddressNotExistException exception){
        log.error("Address Location is not valid");
        ErrorDTO error = new ErrorDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
