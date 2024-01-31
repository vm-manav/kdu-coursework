package com.example.springjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UsersResponseDTO {
    private String username;
    private boolean loggedIn;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
    private Instant createdAt;
    private Instant updateAt;
}
