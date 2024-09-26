package com.example.springjdbc.controller;

import com.example.springjdbc.dto.AllDetailsRequestDTO;
import com.example.springjdbc.services.Appservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {
    private Appservice appservice;

    @Autowired
    public AppController(Appservice appservice) {
        this.appservice=appservice;
    }

    @PostMapping("/add-all-data")
    public ResponseEntity<String> adddDataToAllTables(@RequestBody AllDetailsRequestDTO allDetailsRequestDTO) {
        appservice.insertAllData(allDetailsRequestDTO);
        return ResponseEntity.ok("Data added to all tables successfully");
    }

}
