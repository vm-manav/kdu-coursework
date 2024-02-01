package com.example.springjpa.controller;

import com.example.springjpa.entities.ShiftUser;
import com.example.springjpa.services.ShiftUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;


@RestController
public class ShiftUserController {

    private ShiftUserService shiftUserService;
    public ShiftUserController (ShiftUserService shiftUserService) {
        this.shiftUserService=shiftUserService;
    }
    @PostMapping("/add-shift-user")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.insertIntoShiftUser(shiftUser);
        return ResponseEntity.ok("Value added in ShiftUser table successfully");
    }

    @DeleteMapping("delete-shift-user")
    public ResponseEntity<String> deleteShiftUser(@RequestParam String shiftUserId) {
        UUID id=UUID.fromString(shiftUserId);
        shiftUserService.deleteShiftUser(id);
        return ResponseEntity.ok("Shift user deleted");
    }

}
