package com.example.springjdbc.dao;

import com.example.springjdbc.exceptions.customexceptions.NoValueExistInTableException;
import com.example.springjdbc.mappers.ShiftTypeRowMapper;
import com.example.springjdbc.model.ShiftType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Repository
public class ShiftsTypeDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftsTypeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void addShiftType(ShiftType shiftType) {
        String sql = "INSERT INTO shift_types (id,uq_name, description, active,created_by, " +
                "updated_by, time_zone, tenant_id,created_at,updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                shiftType.getId(),
                shiftType.getUqName(),
                shiftType.getDescription(),
                shiftType.isActive(),
                shiftType.getCreatedBy(),
                shiftType.getUpdatedBy(),
                shiftType.getTimeZone(),
                shiftType.getTenantId(),
                Timestamp.from(shiftType.getCreatedAt()),
                Timestamp.from(shiftType.getUpdateAt())
        );
        log.info("shift type added successfully");
    }
    public ShiftType getShiftTypeById(UUID shiftTypeId) {
        String sql = "SELECT * FROM shift_types WHERE id = ?";
        ShiftType shiftType;
        try{
            shiftType= jdbcTemplate.queryForObject(sql, new ShiftTypeRowMapper(),shiftTypeId);
        } catch (Exception ex) {
            throw new NoValueExistInTableException("No such entry found in table shift type wth id "+shiftTypeId);
        }
        log.info("shift type found");
        return shiftType;
    }
}
