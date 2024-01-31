package com.example.springjdbc.dao;

import com.example.springjdbc.exceptions.customexceptions.NoValueExistInTableException;
import com.example.springjdbc.mappers.ShiftUserRowMapper;
import com.example.springjdbc.model.ShiftUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Repository
public class ShiftUserDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void addShiftUser(ShiftUser shiftUser) {
        String sql = "INSERT INTO shift_user (id,shift_id, user_id, tenant_id,created_by, updated_by,created_at,updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                        shiftUser.getId(),
                        shiftUser.getShiftId(),
                        shiftUser.getUserId(),
                        shiftUser.getTenantId(),
                        shiftUser.getCreatedBy(),
                        shiftUser.getUpdatedBy(),
                        Timestamp.from(shiftUser.getCreatedAt()),
                        Timestamp.from(shiftUser.getUpdateAt())
        );
        log.info("shift user added successfully");
    }

    public ShiftUser getShiftUserById(UUID shiftUserId) {
        String sql = "SELECT * FROM shift_user WHERE id = ?";
        ShiftUser shiftUser;
        try {
            shiftUser= jdbcTemplate.queryForObject(sql, new ShiftUserRowMapper(),shiftUserId);
        } catch (Exception ex) {
            throw new NoValueExistInTableException("No such entry in table ShiftUser with id"+shiftUserId);
        }
        log.info("shift user found");
        return shiftUser;
    }
}
