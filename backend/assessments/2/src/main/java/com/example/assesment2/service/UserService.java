package com.example.assesment2.service;

import com.example.assesment2.dao.UserDAO;
import com.example.assesment2.dto.UserRequestDTO;
import com.example.assesment2.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void addUser(UserRequestDTO userRequestDTO){
        Users users=new Users();
        users.setUserName(userRequestDTO.getUserName());
        users.setFullName(userRequestDTO.getFullName());
        users.setEmailId(userRequestDTO.getEmailId());
        users.setPassword(userRequestDTO.getPassword());
        users.setRole(userRequestDTO.getRole());
        users.setCreatedBy(userRequestDTO.getCreatedBy());
        userDAO.save(users);
    }
    public void deleteUser(UUID userId){
        userDAO.deleteById(userId);
    }
}
