package com.example.springjpa.dao;

import com.example.springjpa.entities.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShiftUserDAO extends JpaRepository<ShiftUser, UUID> {

}
