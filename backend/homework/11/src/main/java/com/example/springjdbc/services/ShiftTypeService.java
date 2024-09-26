package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftsTypeDAO;
import com.example.springjdbc.dto.ShiftTypeRequestDTO;
import com.example.springjdbc.dto.ShiftTypeResponseDTO;
import com.example.springjdbc.mappers.DtoToModelMappers;
import com.example.springjdbc.mappers.ModelToDtoMappers;
import com.example.springjdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftTypeService {
    private ShiftsTypeDAO shiftTypeDAO;
    private DtoToModelMappers dtoToModelMappers;
    private ModelToDtoMappers modelToDtoMappers;

    @Autowired
    public ShiftTypeService(ShiftsTypeDAO shiftTypeDAO,DtoToModelMappers dtoToModelMappers,ModelToDtoMappers modelToDtoMappers) {
        this.shiftTypeDAO=shiftTypeDAO;
        this.dtoToModelMappers=dtoToModelMappers;
        this.modelToDtoMappers=modelToDtoMappers;
    }

    public void insertIntoShiftType(ShiftTypeRequestDTO shiftTypeRequestDTO) {
        ShiftType shiftType=dtoToModelMappers.convertShiftTypeDtoToShiftType(shiftTypeRequestDTO);
        shiftTypeDAO.addShiftType(shiftType);
    }
    public ShiftTypeResponseDTO getShiftTypeById(UUID shiftTypeId) {

        ShiftType shiftType=shiftTypeDAO.getShiftTypeById(shiftTypeId);
        return modelToDtoMappers.toResponseDTO(shiftType);
    }
}
