package com.example.springjdbc.controller;

import com.example.springjdbc.dto.UsersRequestDTO;
import com.example.springjdbc.dto.UsersResponseDTO;
import com.example.springjdbc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UsersRequestDTO usersRequestDTO) {
        userService.insertIntoUsers(usersRequestDTO);
        return ResponseEntity.ok("Value added in user table successfully");
    }

    @GetMapping("/get-user")
    public UsersResponseDTO getUser(@RequestParam String userId) {
         UUID userUUID=UUID.fromString(userId);
         return userService.getUserById(userUUID);
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUserData(@RequestParam String userId,@RequestParam String userName,@RequestParam boolean isLoggedIn,@RequestParam String updatedBy) {
        UUID userUUID=UUID.fromString(userId);
        userService.updateUsers(userUUID,userName,isLoggedIn,updatedBy);
        return ResponseEntity.ok("Data updated successfully");
    }

}
