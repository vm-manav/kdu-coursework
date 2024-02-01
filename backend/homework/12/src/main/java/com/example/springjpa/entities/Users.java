package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    @Column(name = "logged_in")
    private boolean loggedIn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "time_zone")
    private String timeZone;

    @OneToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp updatedAt;

}
