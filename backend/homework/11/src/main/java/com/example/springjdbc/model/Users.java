package com.example.springjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private UUID id;
    private String username;
    private boolean loggedIn;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
    private Instant createdAt;
    private Instant updateAt;
}
