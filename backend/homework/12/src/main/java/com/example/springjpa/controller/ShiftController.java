package com.example.springjpa.controller;

import com.example.springjpa.entities.Shifts;
import com.example.springjpa.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ShiftController {
    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService=shiftService;
    }
    @PostMapping("/add-shift")
    public ResponseEntity<String> addShift(@RequestBody Shifts shifts) {
        shiftService.insertIntoShifts(shifts);
        return ResponseEntity.ok("Value added in Shift table successfully");
    }

    @GetMapping("/top-three-shifts")
    public List<Shifts> findTop3Shifts(@RequestParam String startDate, @RequestParam String endDate) {
        Pageable pageable = PageRequest.of(0, 3);
        Date shiftStartDate=Date.valueOf(startDate);
        Date shiftEndDate=Date.valueOf(endDate);
        return shiftService.getTopThreeShifts(shiftStartDate, shiftEndDate, pageable);
    }
}
