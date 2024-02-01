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
@Table(name = "shift_user")
public class ShiftUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "shift_id" , referencedColumnName = "id")
    private Shifts shift;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "tenant_id" , referencedColumnName = "id")
    private Tenant tenant;

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

}
