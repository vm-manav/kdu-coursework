package com.example.assesment2.controller;

import com.example.assesment2.entities.Inventory;
import com.example.assesment2.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody Inventory inventory){
        inventoryService.addProduct(inventory);
        return ResponseEntity.ok("Product Added successfully");
    }

    @PutMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestParam String productName, @RequestParam String description, @RequestParam double price, @RequestParam int quantity, @RequestParam String id){
        UUID uuid=UUID.fromString(id);
        inventoryService.updateProduct(productName,description,price,quantity,uuid);
        return ResponseEntity.ok("product updated successfully");
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam String id){
        UUID uuid=UUID.fromString(id);
        inventoryService.deleteProduct(uuid);
        return ResponseEntity.ok("product deleted successfully");
    }
}
