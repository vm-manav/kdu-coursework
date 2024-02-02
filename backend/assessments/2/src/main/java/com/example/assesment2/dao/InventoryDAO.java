package com.example.assesment2.dao;

import com.example.assesment2.entities.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, UUID> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE inventory SET product_name =?1,description =?2, price=?3 ,quantity=?4 WHERE id =?5", nativeQuery = true)
    void updateUserDetails(String productName,String description,double price,int quantity,UUID id);
}
