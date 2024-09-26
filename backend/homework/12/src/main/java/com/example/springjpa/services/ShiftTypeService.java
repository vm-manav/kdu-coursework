package com.example.springjpa.services;

import com.example.springjpa.dao.ShiftsTypeDAO;
import com.example.springjpa.entities.ShiftType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ShiftTypeService {
    private ShiftsTypeDAO shiftsTypeDAO;

    @Autowired
    public ShiftTypeService(ShiftsTypeDAO shiftsTypeDAO) {
        this.shiftsTypeDAO=shiftsTypeDAO;
    }

    public void insertIntoShiftType(ShiftType shiftType) {
        shiftsTypeDAO.save(shiftType);
        log.info("Shift type added successfully");
    }


}
