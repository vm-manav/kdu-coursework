package com.example.assesment2.dao;

import com.example.assesment2.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<Users, UUID> {
}
