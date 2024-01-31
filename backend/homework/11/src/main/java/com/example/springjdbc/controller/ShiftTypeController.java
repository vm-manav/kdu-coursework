package com.example.springjdbc.controller;

import com.example.springjdbc.dto.ShiftTypeRequestDTO;
import com.example.springjdbc.dto.ShiftTypeResponseDTO;
import com.example.springjdbc.services.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class ShiftTypeController {
    private ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService=shiftTypeService;
    }

    @PostMapping("/add-shift-type")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeRequestDTO shiftTypeRequestDTO) {
        shiftTypeService.insertIntoShiftType(shiftTypeRequestDTO);
        return ResponseEntity.ok("Value added in Shift Type table successfully");
    }

    @GetMapping("/get-shift-type")
    public ShiftTypeResponseDTO getShiftType(@RequestParam String shiftTypeId) {
        UUID userUUID = UUID.fromString(shiftTypeId);
        return shiftTypeService.getShiftTypeById(userUUID);
    }
}
