package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDAO extends JpaRepository<House,Integer> {
    House findById(int id);
}
