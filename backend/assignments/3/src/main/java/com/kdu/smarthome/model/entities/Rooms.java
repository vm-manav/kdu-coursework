package com.kdu.smarthome.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a room in the Smart Home application.
 */
@Entity
@Data
@Table(name = "room")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "room_name")
    private String roomName;

    @OneToMany
    private List<Inventory> inventoryList=new ArrayList<>();

    public void addInventory(Inventory inventory){
        this.inventoryList.add(inventory);
    }
}
