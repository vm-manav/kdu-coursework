package com.example.springjdbc.mappers;

import com.example.springjdbc.model.ShiftUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftUserRowMapper implements RowMapper<ShiftUser> {

        @Override
        public ShiftUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            ShiftUser shiftUser = new ShiftUser();
            shiftUser.setId(UUID.fromString(rs.getString("id")));
            shiftUser.setShiftId(UUID.fromString(rs.getString("shift_id")));
            shiftUser.setUserId(UUID.fromString(rs.getString("user_id")));
            shiftUser.setTenantId(UUID.fromString(rs.getString("tenant_id")));
            shiftUser.setCreatedBy(rs.getString("created_by"));
            shiftUser.setCreatedAt(rs.getTimestamp("created_at").toInstant());
            shiftUser.setUpdatedBy(rs.getString("updated_by"));
            shiftUser.setUpdateAt(rs.getTimestamp("updated_at").toInstant());
            return shiftUser;
        }
    }

