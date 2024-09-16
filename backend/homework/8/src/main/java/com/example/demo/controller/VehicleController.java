package com.example.demo.controller;
import com.example.demo.dto.VehicleDTO;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {
    private VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.insertVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle added successfully!");
    }

    @GetMapping("/vehicle/{vehicleName}")
    public VehicleDTO getUser(@PathVariable String vehicleName)
    {
        return vehicleService.getVehicle(vehicleName);
    }


    @PutMapping("/vehicle")
    public ResponseEntity<String> updateUser(@RequestBody VehicleDTO vehicleDTO)
    {
        vehicleService.updateVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle updated successfully!");
    }

    @DeleteMapping("/vehicle/{vehicleName}")
    public ResponseEntity<String>deleteUser(@PathVariable String vehicleName)
    {
        vehicleService.deleteVehicle(vehicleName);
        return ResponseEntity.ok("Vehicle removed");
    }
}
