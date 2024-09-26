package com.example.springjdbc.dao;

import com.example.springjdbc.exceptions.customexceptions.NoValueExistInTableException;
import com.example.springjdbc.mappers.UserRowMapper;
import com.example.springjdbc.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Repository
public class UsersDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public UsersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }


    public void addUser(Users user) {
        String sql = "INSERT INTO users (id,username, loggedin,created_by, updated_by, time_zone, tenant_id,created_at,updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                        user.getId(),
                        user.getUsername(),
                        user.isLoggedIn(),
                        user.getCreatedBy(),
                        user.getUpdatedBy(),
                        user.getTimeZone(),
                        user.getTenantId(),
                        Timestamp.from(user.getCreatedAt()),
                        Timestamp.from(user.getUpdateAt())
        );
        log.info("user added");
    }
    public Users getUserById(UUID userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        Users user;
        try {
            user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        }catch (Exception ex){
            throw new NoValueExistInTableException("Cannot find the Id "+userId+" in users table");
        }
        log.info("User exist in table");
        return user;
    }

    public void updateUser(UUID userId, String newUsername, boolean isLoggedIn, String updatedBy) {

        String sql = "UPDATE users SET username = ?, loggedin = ?, updated_by = ?,updated_at=? WHERE id = ?";
        int val=jdbcTemplate.update(sql, newUsername, isLoggedIn, updatedBy, Timestamp.from(Instant.now()),userId);
        if(val==0) {
            throw new NoValueExistInTableException("No such id found to update user table");
        }
        log.info("user updated");
    }
}
