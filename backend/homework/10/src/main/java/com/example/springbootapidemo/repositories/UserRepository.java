package com.example.springbootapidemo.repositories;

import com.example.springbootapidemo.exception.customexceptions.NoUsersFoundException;
import com.example.springbootapidemo.exception.customexceptions.UserAlreadyExistException;
import com.example.springbootapidemo.exception.customexceptions.UserNotFoundException;
import com.example.springbootapidemo.model.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class managing the storage and retrieval of User entities in a Spring Boot application.
 * Utilizes an in-memory ArrayList to store users and provides methods for adding users, retrieving a single user by username,
 * and getting a list of all users. Additionally, includes exception handling for scenarios such as user already existing,
 * user not found, and no users present in the database.
 */

@Slf4j
@Repository
public class UserRepository {
    private ArrayList<User> userArrayList;

    @PostConstruct
    public void initialize(){
        userArrayList=new ArrayList<>();
    }

    public void addUserToList(User user) {
        if(userArrayList.contains(user)){
            throw new UserAlreadyExistException("Given user already exist in the database");
        }
        log.info("user added successfully");
        userArrayList.add(user);
    }

    public User getUserFromList(String userName) {
        for(User user : userArrayList) {
            if(user.getUserName().equalsIgnoreCase(userName)) {
                log.info("user found in the database");
                return user;
            }
        }
        throw new UserNotFoundException("No user found with the given username");
    }

    public List<User> getAllUsers() {
        if(userArrayList.isEmpty()) {
            throw new NoUsersFoundException("Database empty no users found");
        }
        log.info("users found in database");
        return userArrayList;
    }
}
