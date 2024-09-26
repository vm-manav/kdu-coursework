package com.example.springjdbc.dao;

import com.example.springjdbc.exceptions.customexceptions.NoValueExistInTableException;
import com.example.springjdbc.mappers.ShiftsRowMapper;
import com.example.springjdbc.model.Shifts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Repository
public class ShiftsDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void addShifts(Shifts shifts) {
        String querry = "INSERT INTO shifts (id,shift_type_id, name, date_start, date_end,time_start, " +
                "time_end, created_by, updated_by, time_zone, tenant_id,created_at,updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(querry,
                shifts.getId(),
                shifts.getShiftTypeId(),
                shifts.getName(),
                shifts.getDateStart(),
                shifts.getDateEnd(),
                shifts.getTimeStart(),
                shifts.getTimeEnd(),
                shifts.getCreatedBy(),
                shifts.getUpdatedBy(),
                shifts.getTimeZone(),
                shifts.getTenantId(),
                Timestamp.from(shifts.getCreatedAt()),
                Timestamp.from(shifts.getUpdateAt())
        );
        log.info("shift added successfully");
    }
    public Shifts getShiftById(UUID shiftId) {
        String sql = "SELECT * FROM shifts WHERE id = ?";
        Shifts shifts;
        try {
            shifts =jdbcTemplate.queryForObject(sql, new ShiftsRowMapper(),shiftId);
        } catch (Exception ex) {
            throw new NoValueExistInTableException("no such entry exist in table shifts with id "+shiftId);
        }
        log.info("Shift found ");
        return shifts;
    }

}
