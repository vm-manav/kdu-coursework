package com.example.springjdbc.dto;
import lombok.Data;

import java.util.UUID;

@Data
public class UsersRequestDTO {
    private String username;
    private boolean loggedIn;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
