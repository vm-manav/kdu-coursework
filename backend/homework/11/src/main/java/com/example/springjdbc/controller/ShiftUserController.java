package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftUserRequestDTO;
import com.example.springjdbc.dto.ShiftUserResponseDTO;
import com.example.springjdbc.services.ShiftUserService;
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
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserRequestDTO shiftUserRequestDTO) {
        shiftUserService.insertIntoShiftUser(shiftUserRequestDTO);
        return ResponseEntity.ok("Value added in ShiftUser table successfully");
    }
    @GetMapping("/get-shift-user")
    public ShiftUserResponseDTO getShiftUser(@RequestParam String shiftUserId) {
        UUID userUUID=UUID.fromString(shiftUserId);
        return shiftUserService.getShiftUserById(userUUID);
    }
}
