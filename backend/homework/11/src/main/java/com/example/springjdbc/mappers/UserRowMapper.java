package com.example.springjdbc.mappers;

import com.example.springjdbc.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs,int rowNum) throws SQLException {
        Users user = new Users();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setUsername(rs.getString("username"));
        user.setLoggedIn(rs.getBoolean("loggedin"));
        user.setCreatedBy(rs.getString("created_by"));
        user.setUpdatedBy(rs.getString("updated_by"));
        user.setTimeZone(rs.getString("time_zone"));
        user.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        user.setCreatedAt(rs.getTimestamp("created_at").toInstant());
        user.setUpdateAt(rs.getTimestamp("updated_at").toInstant());
        return user;
    }
}
