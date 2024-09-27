package com.kdu.smarthome.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a house in the Smart Home application.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String address;
    private String houseName;
    private String adminUserName;

    @ManyToMany
    private List<Users> usersList=new ArrayList<>();

    @OneToMany
    private List<Rooms> roomsList=new ArrayList<>();

    public void addUserToHouse(Users users) {
        this.usersList.add(users);
    }

    public void addRoom(Rooms rooms) {
        this.roomsList.add(rooms);
    }
}
