package com.example.springjdbc.mappers;

import com.example.springjdbc.model.Shifts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftsRowMapper implements RowMapper<Shifts> {

    @Override
    public Shifts mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shifts shift = new Shifts();
        shift.setId(UUID.fromString(rs.getString("id")));
        shift.setShiftTypeId(UUID.fromString(rs.getString("shift_type_id")));
        shift.setName(rs.getString("name"));
        shift.setDateStart(rs.getDate("date_start"));
        shift.setDateEnd(rs.getDate("date_end"));
        shift.setTimeStart(rs.getTime("time_start"));
        shift.setTimeEnd(rs.getTime("time_end"));
        shift.setCreatedBy(rs.getString("created_by"));
        shift.setUpdatedBy(rs.getString("updated_by"));
        shift.setTimeZone(rs.getString("time_zone"));
        shift.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        shift.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        shift.setUpdateAt(rs.getTimestamp("updated_at").toInstant());
        return shift;
    }
}
