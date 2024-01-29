package com.example.springbootapidemo.service;

import com.example.springbootapidemo.dto.RequestDto;
import com.example.springbootapidemo.dto.ResponseDto;
import com.example.springbootapidemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootapidemo.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Includes methods for adding a user, retrieving user details by username, and getting a list of all users.
 */

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }
    /**
     * Adds a new user to the system based on the provided RequestDto.
     * Converts the RequestDto to a User entity and adds it to the UserRepository.
     * @param requestDto The data transfer object containing user information.
     */
    public void addUser(RequestDto requestDto) {
        User user=convertDtoToUser(requestDto);
        userRepository.addUserToList(user);
    }

    /**
     * Retrieves details of a single user by the provided username.
     * Throws a UserNotFoundException if no user is found with the given username.
     * @param userName The username of the user to retrieve.
     * @return ResponseDto containing user details.
     * @throws UserNotFoundException if no user is found with the provided username.
     */
    public ResponseDto getUserDetails(String userName) {
        User user=userRepository.getUserFromList(userName);
        return convertUserTODto(user);
    }

    /**
     * Retrieves a list of all users in the system.
     * Throws a NoUsersFoundException if the user repository is empty.
     * @return List of ResponseDto containing details of all users.
     * @throws NoUsersFoundException if no users are found in the repository.
     */
    public List<ResponseDto> getALlUser() {
        ArrayList<ResponseDto> responseDtoArrayList=new ArrayList<>();
        ArrayList<User> userArrayList= (ArrayList<User>) userRepository.getAllUsers();
        for(User user:userArrayList){
            responseDtoArrayList.add(convertUserTODto(user));
        }
        return responseDtoArrayList;
    }

    /**
     * Converts a RequestDto object to a User entity.
     * @param requestDto The data transfer object containing user information.
     * @return User entity created from the RequestDto.
     */
    public User convertDtoToUser(RequestDto requestDto) {
        User user=new User();
        user.setUserName(requestDto.getUserName());
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        return user;
    }

    /**
     * Converts a User entity to a ResponseDto.
     * @param user The User entity to be converted.
     * @return ResponseDto containing user details.
     */
    public ResponseDto convertUserTODto(User user) {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setUserName(user.getUserName());
        return responseDto;
    }
}
