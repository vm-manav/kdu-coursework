package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftUserResponseDTO {
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updateAt;
}
