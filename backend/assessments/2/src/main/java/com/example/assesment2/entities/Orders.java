package com.example.assesment2.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Users users;

    @OneToOne
    private ShoppingCart shoppingCart;

    @OneToOne
    private Address address;
}
