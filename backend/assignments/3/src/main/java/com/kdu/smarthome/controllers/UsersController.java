package com.kdu.smarthome.controllers;

import com.kdu.smarthome.auth.JwtUtil;
import com.kdu.smarthome.model.dto.requestdto.UsersRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.UserRegistrationResponseDTO;
import com.kdu.smarthome.model.entities.Users;
import com.kdu.smarthome.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling user-related operations in the Smart Home application.
 * Exposes endpoints for user registration and login.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UsersController {
    private UsersService usersService;
    private JwtUtil jwtUtil;

    /**
     * Constructor to initialize UsersController with required services.
     *
     * @param usersService The UsersService instance for user-related operations.
     * @param jwtUtil      The JwtUtil instance for handling JSON Web Token operations.
     */
    @Autowired
    public UsersController(UsersService usersService, JwtUtil jwtUtil){
        this.usersService=usersService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Endpoint for registering a new user.
     *
     * @param usersRequestDTO The UsersRequestDTO containing user details.
     * @return ResponseEntity containing UserRegistrationResponseDTO and HTTP status.
     */
    @PostMapping("/auth/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerNewUser(@RequestBody UsersRequestDTO usersRequestDTO) {
        try {
            Users user=usersService.registerUser(usersRequestDTO);
            String token = jwtUtil.createToken(user);

            UserRegistrationResponseDTO userRegistrationResponseDTO=new UserRegistrationResponseDTO();
            userRegistrationResponseDTO.setMessage("User registered successfully");
            userRegistrationResponseDTO.setToken(token);
            return new ResponseEntity<>(userRegistrationResponseDTO,HttpStatus.OK);

        }catch (BadCredentialsException e){
            UserRegistrationResponseDTO userRegistrationResponseDTO=new UserRegistrationResponseDTO();
            userRegistrationResponseDTO.setMessage("Invalid username or password");
            return new ResponseEntity<>(userRegistrationResponseDTO,HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            UserRegistrationResponseDTO userRegistrationResponseDTO=new UserRegistrationResponseDTO();
            userRegistrationResponseDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(userRegistrationResponseDTO,HttpStatus.UNAUTHORIZED);
        }
    }
}