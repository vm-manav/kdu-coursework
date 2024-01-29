package com.example.springbootapidemo.controller;


import com.example.springbootapidemo.dto.RequestDto;
import com.example.springbootapidemo.dto.ResponseDto;
import com.example.springbootapidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Controller class for handling user-related HTTP requests in a Spring Boot application.
 * Provides endpoints for adding a user, retrieving details of a single user by username, and getting a list of all users.
 */

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody RequestDto requestDto){
        userService.addUser(requestDto);
        return ResponseEntity.ok("User Added successfully!");
    }


    @GetMapping("/username/{userName}")
    public ResponseDto getSingleUser(@PathVariable String userName){
        return userService.getUserDetails(userName);
    }

    @GetMapping("/users")
    public List<ResponseDto> getAllUser() {
        return userService.getALlUser();
    }
}

