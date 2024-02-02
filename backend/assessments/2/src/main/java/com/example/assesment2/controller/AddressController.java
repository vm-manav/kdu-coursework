package com.example.assesment2.controller;

import com.example.assesment2.entities.Address;
import com.example.assesment2.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add-address")
    public ResponseEntity<String> addAddress(@RequestBody Address address ,@RequestParam String userId){
        addressService.addAddress(address,userId);
        return ResponseEntity.ok("Address Added successfully");
    }
    @DeleteMapping("/delete-address")
    public ResponseEntity<String> deleteAddress(@RequestParam String addressId){
        UUID uuid=UUID.fromString(addressId);
        addressService.deleteAddress(uuid);
        return ResponseEntity.ok("Address Deleted successfully");
    }
}
