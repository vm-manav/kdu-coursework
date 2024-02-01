package com.example.springjpa.services;

import com.example.springjpa.dao.ShiftsDAO;
import com.example.springjpa.entities.Shifts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j
public class ShiftService {
    private ShiftsDAO shiftsDAO;

    @Autowired
    public ShiftService(ShiftsDAO shiftsDAO) {
        this.shiftsDAO=shiftsDAO;
    }
    public void insertIntoShifts(Shifts shifts) {
        shiftsDAO.save(shifts);
        log.info("Shift added successfully");
    }

    public List<Shifts> getTopThreeShifts(Date startDate, Date endDate, Pageable pageable) {
        log.info("Shifts found");
        return shiftsDAO.findTopThreeShiftsByDateRange(startDate,endDate,pageable);
    }
}
