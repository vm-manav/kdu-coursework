package com.example.springjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftUser {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updateAt;
}
