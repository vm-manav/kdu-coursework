package com.example.springjdbc.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
public class ShiftsRequestDTO {
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private String createdBy;
    private String updatedBy;
    private String timeZone;
    private UUID tenantId;

}
