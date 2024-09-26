package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftsDAO;
import com.example.springjdbc.dto.ShiftsRequestDTO;
import com.example.springjdbc.dto.ShiftsResponseDTO;
import com.example.springjdbc.mappers.DtoToModelMappers;
import com.example.springjdbc.mappers.ModelToDtoMappers;
import com.example.springjdbc.model.Shifts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftService {
    private ShiftsDAO shiftsDAO;
    private DtoToModelMappers dtoToModelMappers;
    private ModelToDtoMappers modelToDtoMappers;

    @Autowired
    public ShiftService(ShiftsDAO shiftsDAO, DtoToModelMappers dtoToModelMappers,ModelToDtoMappers modelToDtoMappers) {
        this.shiftsDAO=shiftsDAO;
        this.dtoToModelMappers=dtoToModelMappers;
        this.modelToDtoMappers=modelToDtoMappers;
    }
    public void insertIntoShifts(ShiftsRequestDTO shiftsRequestDTO) {
        Shifts shifts=dtoToModelMappers.convertShiftDtoToShift(shiftsRequestDTO);
        shiftsDAO.addShifts(shifts);
    }

    public ShiftsResponseDTO getShiftById(UUID shiftId) {
        Shifts shifts= shiftsDAO.getShiftById(shiftId);
        return modelToDtoMappers.toResponseDTO(shifts);
    }
}
