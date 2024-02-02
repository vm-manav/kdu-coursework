package com.example.assesment2.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp updatedAt;

    @OneToOne
    private Users users;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inventory> inventoryList=new ArrayList<>();

    public void addToCart(Inventory inventory) {
        inventoryList.add(inventory);
    }
    public void removeFromCart(Inventory inventory) {
        inventoryList.remove(inventory);
    }
}
