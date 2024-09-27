package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsDAO extends JpaRepository<Rooms,Integer> {
    Rooms findById(int id);
}
