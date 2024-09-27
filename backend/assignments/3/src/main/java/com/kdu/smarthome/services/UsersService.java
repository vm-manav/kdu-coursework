package com.kdu.smarthome.services;

import com.kdu.smarthome.dao.UsersDAO;
import com.kdu.smarthome.exception.customexceptions.UserAlreadyExistException;
import com.kdu.smarthome.model.dto.requestdto.UsersRequestDTO;
import com.kdu.smarthome.model.entities.Users;
import com.kdu.smarthome.model.mappers.DtoToEntityMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing user-related operations in the Smart Home application.
 */
@Service
@Slf4j
public class UsersService implements UserDetailsService {
    private UsersDAO usersDAO;
    private DtoToEntityMappers dtoToEntityMappers;

    /**
     * Constructs a UsersService with the specified UsersDAO and DtoToEntityMappers.
     *
     * @param usersDAO             The UsersDAO for accessing user data in the database.
     * @param dtoToEntityMappers   The DtoToEntityMappers for converting DTOs to entities.
     */
    @Autowired
    public UsersService(UsersDAO usersDAO,DtoToEntityMappers dtoToEntityMappers) {
        this.usersDAO=usersDAO;
        this.dtoToEntityMappers=dtoToEntityMappers;
    }
    /**
     * Loads a user by their username.
     *
     * @param userName The username of the user to load.
     * @return A UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = usersDAO.findByUsername(userName);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
    }

    /**
     * Registers a new user based on the provided UsersRequestDTO.
     *
     * @param usersRequestDTO The UsersRequestDTO containing user details.
     * @return The registered user entity.
     * @throws UserAlreadyExistException If the user with the given username already exists.
     */
    public Users registerUser(UsersRequestDTO usersRequestDTO) {
        Users user =usersDAO.findByUsername(usersRequestDTO.getUsername());
        if(user!=null) {
            throw new UserAlreadyExistException("user with given username "+usersRequestDTO.getUsername()+" already exist");
        }
        Users registeredUser=dtoToEntityMappers.convertUserRequestDtoToUser(usersRequestDTO);
        log.info("user registered successfully");
        usersDAO.save(registeredUser);
        return registeredUser;
    }
}
