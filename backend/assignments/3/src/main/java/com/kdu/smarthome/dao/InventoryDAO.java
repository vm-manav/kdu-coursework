package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory,String> {
    Inventory findByKickstonId(String kickstonID);
}
