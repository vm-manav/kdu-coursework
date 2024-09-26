package com.example.springjpa.controller;


import com.example.springjpa.entities.Users;
import com.example.springjpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<String> addUser(@RequestBody Users users) {
        userService.insertIntoUsers(users);
        return ResponseEntity.ok("Value added in user table successfully");
    }

    @GetMapping("/get-all-users")
    public Page<Users> getAllUsers(@RequestParam(defaultValue = "0") int numberOfPages, @RequestParam(defaultValue = "50") int pageSize) {
        numberOfPages=Math.max(numberOfPages,0);
        pageSize=Math.min(50,Math.max(1,pageSize));
        Pageable pageable = PageRequest.of(numberOfPages,pageSize);
        return userService.getAllPages(pageable);
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUserData(@RequestParam String userId,@RequestParam String userName,@RequestParam boolean isLoggedIn,@RequestParam String updatedBy) {
        UUID userUUID=UUID.fromString(userId);
        userService.updateUsers(userUUID,userName,isLoggedIn,updatedBy);
        return ResponseEntity.ok("Data updated successfully");
    }

}
