package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftUserDAO;
import com.example.springjdbc.dao.ShiftsDAO;
import com.example.springjdbc.dao.ShiftsTypeDAO;
import com.example.springjdbc.dao.UsersDAO;
import com.example.springjdbc.dto.AllDetailsRequestDTO;
import com.example.springjdbc.mappers.DtoToModelMappers;
import com.example.springjdbc.model.ShiftType;
import com.example.springjdbc.model.ShiftUser;
import com.example.springjdbc.model.Shifts;
import com.example.springjdbc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Appservice {
    private DtoToModelMappers dtoToModelMappers;
    private ShiftsDAO shiftsDAO;
    private ShiftsTypeDAO shiftsTypeDAO;
    private ShiftUserDAO shiftUserDAO;
    private UsersDAO usersDAO;

    @Autowired
    public Appservice(DtoToModelMappers dtoToModelMappers,ShiftsDAO shiftsDAO,ShiftsTypeDAO shiftsTypeDAO,ShiftUserDAO shiftUserDAO,UsersDAO usersDAO) {
        this.dtoToModelMappers=dtoToModelMappers;
        this.shiftsDAO=shiftsDAO;
        this.usersDAO=usersDAO;
        this.shiftsTypeDAO=shiftsTypeDAO;
        this.shiftUserDAO=shiftUserDAO;
    }
    public void insertAllData(AllDetailsRequestDTO allDetailsRequestDTO) {
        Users user=new Users();
        Shifts shifts=new Shifts();
        ShiftType shiftType=new ShiftType();
        ShiftUser shiftUser=new ShiftUser();
        dtoToModelMappers.convertAllDetailsDtoToModel(user,shifts,shiftType,shiftUser,allDetailsRequestDTO);
        shiftsTypeDAO.addShiftType(shiftType);
        shiftsDAO.addShifts(shifts);
        usersDAO.addUser(user);
        shiftUserDAO.addShiftUser(shiftUser);
    }
}
