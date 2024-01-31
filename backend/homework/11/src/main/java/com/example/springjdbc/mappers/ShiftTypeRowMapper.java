package com.example.springjdbc.mappers;

import com.example.springjdbc.model.ShiftType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftTypeRowMapper implements RowMapper<ShiftType> {

    @Override
    public
    ShiftType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftType shiftType = new ShiftType();
        shiftType.setId(UUID.fromString(rs.getString("id")));
        shiftType.setUqName(rs.getString("uq_name"));
        shiftType.setDescription(rs.getString("description"));
        shiftType.setActive(rs.getBoolean("active"));
        shiftType.setCreatedBy(rs.getString("created_by"));
        shiftType.setUpdatedBy(rs.getString("updated_by"));
        shiftType.setTimeZone(rs.getString("time_zone"));
        shiftType.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        shiftType.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        shiftType.setUpdateAt(rs.getTimestamp("updated_at").toInstant());
        return shiftType;
    }
}

