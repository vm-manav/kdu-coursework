package com.example.springjdbc.services;

import com.example.springjdbc.dao.UsersDAO;
import com.example.springjdbc.dto.*;
import com.example.springjdbc.mappers.DtoToModelMappers;
import com.example.springjdbc.mappers.ModelToDtoMappers;
import com.example.springjdbc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {



    private UsersDAO usersDAO;
    private DtoToModelMappers dtoToModelMappers;
    private ModelToDtoMappers modelToDtoMappers;

    @Autowired
    public UserService(UsersDAO usersDAO,DtoToModelMappers dtoToModelMappers,ModelToDtoMappers modelToDtoMappers) {
        this.usersDAO=usersDAO;
        this.dtoToModelMappers=dtoToModelMappers;
        this.modelToDtoMappers=modelToDtoMappers;
    }

    public void insertIntoUsers(UsersRequestDTO usersRequestDTO) {
        Users users=dtoToModelMappers.convertUserDtoToUser(usersRequestDTO);
        usersDAO.addUser(users);
    }
    public UsersResponseDTO getUserById(UUID userId) {
        Users users= usersDAO.getUserById(userId);
        return modelToDtoMappers.toResponseDTO(users);
    }

    public void updateUsers(UUID userId,String userName,boolean isLoggedIn,String updatedBy) {
        usersDAO.updateUser(userId,userName,isLoggedIn,updatedBy);
    }

}
