package com.example.springjpa.dao;

import com.example.springjpa.entities.Shifts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftsDAO extends JpaRepository<Shifts, UUID> {

    @Query(value = "SELECT * FROM Shifts s " +
            "WHERE s.date_start = :startDate " +
            "AND s.date_end = :endDate " +
            "ORDER BY s.name ASC", nativeQuery = true)
    List<Shifts> findTopThreeShiftsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

}
