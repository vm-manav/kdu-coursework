package com.example.springjdbc.services;

import com.example.springjdbc.dao.ShiftUserDAO;
import com.example.springjdbc.dto.ShiftUserRequestDTO;
import com.example.springjdbc.dto.ShiftUserResponseDTO;
import com.example.springjdbc.mappers.DtoToModelMappers;
import com.example.springjdbc.mappers.ModelToDtoMappers;
import com.example.springjdbc.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftUserService {
    private ShiftUserDAO shiftUserDAO;
    private DtoToModelMappers dtoToModelMappers;
    private ModelToDtoMappers modelToDtoMappers;

    @Autowired
    public ShiftUserService(ShiftUserDAO shiftUserDAO, DtoToModelMappers dtoToModelMappers, ModelToDtoMappers modelToDtoMappers) {
        this.shiftUserDAO=shiftUserDAO;
        this.dtoToModelMappers=dtoToModelMappers;
        this.modelToDtoMappers=modelToDtoMappers;
    }
    public void insertIntoShiftUser(ShiftUserRequestDTO shiftUserRequestDTO) {
        ShiftUser shiftUser=dtoToModelMappers.convertShiftUserDtoToShiftUser(shiftUserRequestDTO);
        shiftUserDAO.addShiftUser(shiftUser);
    }
    public ShiftUserResponseDTO getShiftUserById(UUID shiftUserId) {
        ShiftUser shiftUser = shiftUserDAO.getShiftUserById(shiftUserId);
        return modelToDtoMappers.toResponseDTO(shiftUser);
    }
}
