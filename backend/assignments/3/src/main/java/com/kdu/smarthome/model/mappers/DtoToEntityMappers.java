package com.kdu.smarthome.model.mappers;

import com.kdu.smarthome.dao.UsersDAO;
import com.kdu.smarthome.exception.customexceptions.UserNotFoundException;
import com.kdu.smarthome.model.dto.requestdto.HouseRequestDTO;
import com.kdu.smarthome.model.dto.requestdto.InventoryRequestDTO;
import com.kdu.smarthome.model.dto.requestdto.RoomRequestDTO;
import com.kdu.smarthome.model.dto.requestdto.UsersRequestDTO;
import com.kdu.smarthome.model.entities.House;
import com.kdu.smarthome.model.entities.Inventory;
import com.kdu.smarthome.model.entities.Rooms;
import com.kdu.smarthome.model.entities.Users;
import com.kdu.smarthome.utills.TimeStampConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
/**
 * Mapper class responsible for converting DTOs to corresponding entity classes.
 */
@Component
@Slf4j
public class DtoToEntityMappers {
    private UsersDAO usersDAO;
    @Autowired
    public DtoToEntityMappers(UsersDAO usersDAO){
        this.usersDAO=usersDAO;
    }

    /**
     * Converts a UsersRequestDTO to a Users entity.
     *
     * @param usersRequestDTO The input UsersRequestDTO.
     * @return A Users entity.
     */
    public Users convertUserRequestDtoToUser(UsersRequestDTO usersRequestDTO) {
        Users user=new Users();
        user.setName(usersRequestDTO.getName());
        user.setFirstName(usersRequestDTO.getFirstName());
        user.setLastName(usersRequestDTO.getLastName());
        user.setEmailId(usersRequestDTO.getEmailId());
        user.setUsername(usersRequestDTO.getUsername());
        user.setPassword(usersRequestDTO.getPassword());
        user.setRole("ROLE_APPUSER");
        return user;
    }

    /**
     * Converts a HouseRequestDTO to a House entity.
     *
     * @param houseRequestDTO The input HouseRequestDTO.
     * @return A House entity.
     * @throws UserNotFoundException If the associated user is not found.
     */
    public House convertHouseRequesDtoToHouse(HouseRequestDTO houseRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Users user=usersDAO.findByUsername(userName);
        if(user==null) {
            throw new UserNotFoundException("User not found");
        }
        House house=new House();
        house.setHouseName(houseRequestDTO.getHouseName());
        house.setAddress(houseRequestDTO.getAddress());
        house.setAdminUserName(userName);
        house.addUserToHouse(user);
        return house;
    }

    /**
     * Converts a RoomRequestDTO to a Rooms entity.
     *
     * @param roomRequestDTO The input RoomRequestDTO.
     * @return A Rooms entity.
     */
    public Rooms convertRoomRequestDtoToRoom(RoomRequestDTO roomRequestDTO){
        Rooms rooms=new Rooms();
        rooms.setRoomName(roomRequestDTO.getRoomName());
        return  rooms;
    }

    /**
     * Converts an InventoryRequestDTO to an Inventory entity.
     *
     * @param inventoryRequestDTO The input InventoryRequestDTO.
     * @return An Inventory entity.
     */
    public Inventory convertInventoryRequestDtoToInventory(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory=new Inventory();
        inventory.setRegistered(false);
        inventory.setDevicePassword(inventoryRequestDTO.getDevicePassword());
        inventory.setManufacturePlace(inventoryRequestDTO.getManufacturePlace());
        String before=inventoryRequestDTO.getManufactureDateTime();
        log.info(before);
        String after=TimeStampConvertor.convertTimestampFormat(before);
        log.info(after);
        Timestamp timestamp=Timestamp.valueOf(after);
        log.info(timestamp.toString());
        inventory.setManufactureDateTime(Timestamp.valueOf(TimeStampConvertor.convertTimestampFormat(inventoryRequestDTO.getManufactureDateTime())));
        inventory.setKickstonId(inventoryRequestDTO.getKickstonId());
        inventory.setDeviceUserName(inventoryRequestDTO.getDeviceUserName());
        return inventory;
    }
}
