package com.example.springjpa.services;


import com.example.springjpa.dao.ShiftUserDAO;
import com.example.springjpa.entities.ShiftUser;
import com.example.springjpa.exceptions.customexceptions.NoShiftUserFoundWithGivenIdException;
import com.example.springjpa.exceptions.customexceptions.NoUserFoundWithGivenShiftTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ShiftUserService {
    private ShiftUserDAO shiftUserDAO;

    @Autowired
    public ShiftUserService(ShiftUserDAO shiftUserDAO) {
        this.shiftUserDAO=shiftUserDAO;
    }
    public void insertIntoShiftUser(ShiftUser shiftUser) {
        shiftUserDAO.save(shiftUser);
        log.info("Shift user added successfully");
    }


    public void deleteShiftUser(UUID shiftUserId) {
        Optional<ShiftUser> shiftUserOptional = shiftUserDAO.findById(shiftUserId);

        if (shiftUserOptional.isPresent()) {
            ShiftUser shiftUser = shiftUserOptional.get();
            if (shiftUser.getShift().getTimeEnd().equals(Time.valueOf("23:00:00"))) {
                shiftUserDAO.delete(shiftUser);
            } else {
                throw new NoUserFoundWithGivenShiftTimeException("ShiftUser does not work in a shift ending at 23:00 UTC.");
            }
        } else {
            throw new NoShiftUserFoundWithGivenIdException("No shift user exist with given ID");
        }
        log.info("Shift User deleted");
    }

}
