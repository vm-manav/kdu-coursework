package com.example.springjpa.controller;

import com.example.springjpa.entities.ShiftType;
import com.example.springjpa.services.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ShiftTypeController {
    private ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService=shiftTypeService;
    }

    @PostMapping("/add-shift-type")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.insertIntoShiftType(shiftType);
        return ResponseEntity.ok("Value added in Shift Type table successfully");
    }

}
