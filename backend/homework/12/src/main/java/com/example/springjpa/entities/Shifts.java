package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shifts")
public class Shifts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "shift_type_id" , referencedColumnName = "id")
    private ShiftType shiftType;

    private String name;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "time_start")
    private Time timeStart;

    @Column(name = "time_end")
    private Time timeEnd;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "time_zone")
    private String timeZone;

    @OneToOne
    @JoinColumn(name = "tenant_id" , referencedColumnName = "id")
    private Tenant tenant;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp updatedAt;


}