package com.example.assesment2.service;

import com.example.assesment2.dao.InventoryDAO;
import com.example.assesment2.entities.Inventory;
import com.example.assesment2.exceptions.customexceptions.NoEntityFoundWithGivenIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private InventoryDAO inventoryDAO;

    public void addProduct(Inventory inventory){
        inventoryDAO.save(inventory);
    }

    public void updateProduct(String productName,String description,double price,int quantity,UUID id) {
        Optional<Inventory> inventory=inventoryDAO.findById(id);
        int newQuantity=quantity;
        if (inventory.isPresent()) {
            Inventory inventory1 = inventory.get();
            int quantityInventory=inventory1.getQuantity();
            if(newQuantity<20 || quantityInventory<20) {
                newQuantity=20;
            }
        } else {
            throw new NoEntityFoundWithGivenIdException("No shift user exist with given ID");
        }
        inventoryDAO.updateUserDetails(productName,description,price,newQuantity,id);
    }

    public void deleteProduct(UUID uuid) {
        inventoryDAO.deleteById(uuid);
    }
}
