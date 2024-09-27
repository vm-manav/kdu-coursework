package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<Users,String> {
    Users findByUsername(String username);
}
