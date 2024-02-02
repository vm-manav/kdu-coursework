package com.example.assesment2.controller;

import com.example.assesment2.dto.UserRequestDTO;
import com.example.assesment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addProduct(@RequestBody UserRequestDTO userRequestDTO){
        userService.addUser(userRequestDTO);
        return ResponseEntity.ok("User Added successfully");
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam String userId){
        UUID uuid=UUID.fromString(userId);
        userService.deleteUser(uuid);
        return ResponseEntity.ok("User Deleted successfully");
    }

}
