package com.kdu.smarthome.exception;

import com.kdu.smarthome.exception.customexceptions.*;
import com.kdu.smarthome.model.dto.responsedto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

/**
 * Global exception handler for handling custom exceptions in the Smart Home application.
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandel {

    /**
     * Handles the exception when a user is not found.
     *
     * @param ex The UserNotFoundException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoUsersFound(UserNotFoundException ex){
        log.error("No users present in the list");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the exception when a user already exists.
     *
     * @param ex The UserAlreadyExistException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionUserAlreadyExist(UserAlreadyExistException ex){
        log.error("User already exist in the list");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the exception when a house is not found.
     *
     * @param ex The HouseNotFoundException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {HouseNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionHouseNotFound(HouseNotFoundException ex){
        log.error("No such House found with given id");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the exception when an inventory is not found.
     *
     * @param ex The InventoryNotFoundException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {InventoryNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionInventoryNotFound(InventoryNotFoundException ex){
        log.error("No such Inventory found with given id");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the exception when a room is not found.
     *
     * @param ex The RoomNotFoundException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {RoomNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionRoomNotFound(RoomNotFoundException ex){
        log.error("No such Inventory found with given id");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles the exception when a username or password is invalid.
     *
     * @param ex The UsernamePasswordInvalidException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {UsernamePasswordInvalidException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionUserNamePasswordInvalidFound(UsernamePasswordInvalidException ex){
        log.error("Username or password is invalid");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles the exception when no authentication is found.
     *
     * @param ex The NoAuthenticationException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {NoAuthenticationException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoAuthenticationFound(NoAuthenticationException ex){
        log.error("Not authenticated");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles the exception when access is denied.
     *
     * @param ex The AccessDeniedException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionNoAccessFound(AccessDeniedException ex){
        log.error("Not authenticated");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    /**
     * Handles the exception when an invalid ID is found.
     *
     * @param ex The NumberFormatException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */

    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionInValidIdFound(NumberFormatException ex){
        log.error("Id not valid ");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    /**
     * Handles the exception when a device is not registered.
     *
     * @param ex The DeviceNotRegisteredException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {DeviceNotRegisteredException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionDeviceNotRegistered(DeviceNotRegisteredException ex){
        log.error("The Device is not registered");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    /**
     * Handles the exception when a device is not found with the given username.
     *
     * @param ex The DeviceNotFoundWithUserNameException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status.
     */
    @ExceptionHandler(value = {DeviceNotFoundWithUserNameException.class})
    public ResponseEntity<ErrorDTO> handleCustomExceptionDeviceNotRegistered(DeviceNotFoundWithUserNameException ex){
        log.error("username not matches");
        ErrorDTO error = new ErrorDTO();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    /**
     * Handles authentication exception.
     *
     * @param ex The AuthenticationException instance.
     * @return ResponseEntity containing an error message and HTTP status.
     */
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<String> handleAuthenticationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed at controller advice");
    }
}
