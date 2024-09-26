package com.example.springjpa.dao;

import com.example.springjpa.entities.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface UsersDAO extends JpaRepository<Users, UUID> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = :username, logged_in = :loggedIn, updated_by= :updatedBy WHERE id = :userId", nativeQuery = true)
    void updateUserDetails(@Param("userId") UUID userId, @Param("username") String username, @Param("loggedIn") boolean loggedIn,@Param("updatedBy") String updatedBy);
}
