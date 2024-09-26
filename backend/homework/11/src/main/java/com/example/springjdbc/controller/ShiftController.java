package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftsRequestDTO;
import com.example.springjdbc.dto.ShiftsResponseDTO;
import com.example.springjdbc.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ShiftController {
    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService=shiftService;
    }
    @PostMapping("/add-shift")
    public ResponseEntity<String> addShift(@RequestBody ShiftsRequestDTO shiftsRequestDTO) {
        shiftService.insertIntoShifts(shiftsRequestDTO);
        return ResponseEntity.ok("Value added in Shift table successfully");
    }

    @GetMapping("/get-shift")
    public ShiftsResponseDTO getShifts(@RequestParam String shiftId) {
        UUID userUUID = UUID.fromString(shiftId);
        return shiftService.getShiftById(userUUID);
    }
}
