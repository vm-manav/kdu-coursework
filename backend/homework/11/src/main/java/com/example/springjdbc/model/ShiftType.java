package com.example.springjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftType {
    private UUID id;
    private String uqName;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
    private Instant createdAt;
    private Instant updateAt;
}
