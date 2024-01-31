package com.example.springjdbc.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ShiftUserRequestDTO {
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
    private String createdBy;
    private String updatedBy;
}
