package com.example.springjdbc.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class AllDetailsRequestDTO {
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private String uqName;
    private String description;
    private boolean active;
    private String username;
    private boolean loggedIn;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;
}
