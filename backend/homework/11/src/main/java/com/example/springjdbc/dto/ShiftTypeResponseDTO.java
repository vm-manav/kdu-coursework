package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftTypeResponseDTO {
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
