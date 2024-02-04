package com.kdu.smarthome.services;

import com.kdu.smarthome.dao.HouseDAO;
import com.kdu.smarthome.dao.UsersDAO;
import com.kdu.smarthome.exception.customexceptions.HouseNotFoundException;
import com.kdu.smarthome.exception.customexceptions.UserNotFoundException;
import com.kdu.smarthome.model.dto.requestdto.HouseRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.HouseResponseObjectDTO;
import com.kdu.smarthome.model.dto.responsedto.InventoryResponseObjectDTO;
import com.kdu.smarthome.model.dto.responsedto.RoomResponseObjectDTO;
import com.kdu.smarthome.model.entities.House;
import com.kdu.smarthome.model.entities.Inventory;
import com.kdu.smarthome.model.entities.Rooms;
import com.kdu.smarthome.model.entities.Users;
import com.kdu.smarthome.model.mappers.DtoToEntityMappers;
import com.kdu.smarthome.model.mappers.EntityToDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing house-related operations in the Smart Home application.
 */
@Slf4j
@Service
public class HouseService {
    private DtoToEntityMappers dtoToEntityMappers;
    private EntityToDtoMapper entityToDtoMapper;
    private HouseDAO houseDAO;
    private UsersDAO usersDAO;
    /**
     * Constructs a HouseService with the specified DtoToEntityMappers, HouseDAO, UsersDAO, and EntityToDtoMapper.
     *
     * @param dtoToEntityMappers  The DtoToEntityMappers for converting DTOs to entities.
     * @param houseDAO            The HouseDAO for accessing house data in the database.
     * @param usersDAO            The UsersDAO for accessing user data in the database.
     * @param entityToDtoMapper   The EntityToDtoMapper for converting entities to DTOs.
     */
    @Autowired
    public HouseService(DtoToEntityMappers dtoToEntityMappers,HouseDAO houseDAO,UsersDAO usersDAO,EntityToDtoMapper entityToDtoMapper) {
        this.usersDAO=usersDAO;
        this.dtoToEntityMappers=dtoToEntityMappers;
        this.houseDAO=houseDAO;
        this.entityToDtoMapper=entityToDtoMapper;
    }
    /**
     * Creates a new house based on the provided HouseRequestDTO.
     *
     * @param houseRequestDTO The HouseRequestDTO containing house details.
     * @return The created house entity.
     */
    public House createHouse(HouseRequestDTO houseRequestDTO) {
        House house=dtoToEntityMappers.convertHouseRequesDtoToHouse(houseRequestDTO);
        houseDAO.save(house);
        log.info("house created successfully");
        return house;
    }

    /**
     * Adds a user to the specified house.
     *
     * @param houseRequestDTO The HouseRequestDTO containing user details.
     * @param houseId         The ID of the house to which the user will be added.
     */
    public void addUserToHouse(HouseRequestDTO houseRequestDTO,String houseId) {
        int id=Integer.parseInt(houseId);
        House house=houseDAO.findById(id);
        if(house==null) {
            throw new HouseNotFoundException("House with given id not found");
        }
        Users user=usersDAO.findByUsername(houseRequestDTO.getUsername());
        if(user==null){
            throw new UserNotFoundException("user with given username not found");
        }
        house.addUserToHouse(user);
        houseDAO.save(house);
        log.info("user added to house successfully");
    }

    /**
     * Retrieves a list of all houses in the system.
     *
     * @return The list of all houses.
     */
    public List<HouseResponseObjectDTO> getAllHouses(){
        log.debug("List of houses fetched");
        return entityToDtoMapper.convertFromHouseToResponseDto(houseDAO.findAll());
    }

    /**
     * Updates the address of the specified house.
     *
     * @param houseId         The ID of the house to update.
     * @param houseRequestDTO The HouseRequestDTO containing the new address.
     * @return The updated house entity.
     */
    public House updateAddress(String houseId,HouseRequestDTO houseRequestDTO) {
        int id=Integer.parseInt(houseId);
        House house=houseDAO.findById(id);
        if(house==null) {
            throw new HouseNotFoundException("house with given id not found");
        }
        house.setAddress(houseRequestDTO.getAddress());
        houseDAO.save(house);
        log.info("address updated successfully");
        return house;
    }
    /**
     * Retrieves a list of rooms associated with the specified house ID.
     *
     * @param houseID The ID of the house.
     * @return The list of rooms.
     */
    public List<RoomResponseObjectDTO> getListOfRooms(String houseID){
        House house=houseDAO.findById(Integer.parseInt(houseID));
        if(house==null) {
            throw new HouseNotFoundException("house with given Id not found");
        }
        log.info("fetched rooms list from a house");
        return entityToDtoMapper.convertFromRoomToResposonseDTO(house.getRoomsList());
    }

    public List<Rooms> getALlRooms(String houseID){
        House house=houseDAO.findById(Integer.parseInt(houseID));
        if(house==null) {
            throw new HouseNotFoundException("house with given Id not found");
        }
        log.info("fetched all rooms in home");
        return house.getRoomsList();
    }

    public List<InventoryResponseObjectDTO> getInventory(List<Rooms> roomsList) {
        List<Inventory> inventoryList=new ArrayList<>();
        for (Rooms room:roomsList) {
            List<Inventory> inventoryList1=room.getInventoryList();
            inventoryList.addAll(inventoryList1);
        }
        log.info("Fetched all devices in the home");
        return entityToDtoMapper.convertFromInventoryToResponseDto(inventoryList);
    }
    /**
     * Retrieves a list of devices (inventory) associated with the specified list of rooms.
     *
     * @param roomsList The list of rooms.
     * @return The list of devices.
     */
    public List<Inventory> getListOfDevices(List<Rooms> roomsList){
        List<Inventory> inventoryList=new ArrayList<>();
        for (Rooms room:roomsList) {
            List<Inventory> inventoryList1=room.getInventoryList();
            inventoryList.addAll(inventoryList1);
        }
        log.debug("List of devices fetched");
        return inventoryList;
    }
    /**
     * Retrieves a house based on the provided ID.
     *
     * @param id The ID of the house to retrieve.
     * @return The retrieved house.
     */
    public House getHouseById(int id) {
        log.debug("house found with given id");
        return houseDAO.findById(id);
    }

    /**
     * Saves the provided house entity.
     *
     * @param house The house entity to save.
     */
    public void saveHouse(House house) {
        log.debug("house saved");
        houseDAO.save(house);
    }

    /**
     * Authenticates the user as an admin for the specified house.
     *
     * @param author   The username of the user making the request.
     * @param houseId  The ID of the house.
     * @throws HouseNotFoundException If the house with the given ID is not found.
     * @throws HouseNotFoundException If the user is not an admin for the specified house.
     */
    public void authenticateAdmin(String author,int houseId){
        House house=houseDAO.findById(houseId);
        if(house==null) {
            throw new HouseNotFoundException("house with given Id not found");
        }
        if(!house.getAdminUserName().equals(author)) {
            throw new HouseNotFoundException("the user is not admin");
        }
        log.info("Authentication passes");
    }
}
