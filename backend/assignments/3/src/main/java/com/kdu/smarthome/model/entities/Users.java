package com.kdu.smarthome.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user in the Smart Home application.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "role")
    private String role;
}