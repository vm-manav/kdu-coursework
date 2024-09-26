package com.example.springjdbc.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ShiftTypeRequestDTO {
    private String uqName;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
