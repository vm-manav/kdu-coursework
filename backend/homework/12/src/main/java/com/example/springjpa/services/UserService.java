package com.example.springjpa.services;

import com.example.springjpa.dao.UsersDAO;
import com.example.springjpa.entities.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {
    private UsersDAO usersDAO;

    @Autowired
    public UserService(UsersDAO usersDAO) {
        this.usersDAO=usersDAO;
    }

    public void insertIntoUsers(Users users) {
        usersDAO.save(users);
        log.info("User added successfully");
    }

    public Page<Users> getAllPages(Pageable pageable) {
        log.info("Pages found");
        return usersDAO.findAll(pageable);
    }
    public void updateUsers(UUID userId, String userName, boolean isLoggedIn, String updatedBy) {
        usersDAO.updateUserDetails(userId,userName,isLoggedIn,updatedBy);
        log.info("User updated");
    }


}
