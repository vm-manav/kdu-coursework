package com.kdu.smarthome.services;

import com.kdu.smarthome.dao.InventoryDAO;
import com.kdu.smarthome.exception.customexceptions.*;
import com.kdu.smarthome.model.dto.requestdto.AddDeviceToRoomRequestDTO;
import com.kdu.smarthome.model.dto.requestdto.InventoryRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.InventoryResponseObjectDTO;
import com.kdu.smarthome.model.entities.House;
import com.kdu.smarthome.model.entities.Inventory;
import com.kdu.smarthome.model.entities.Rooms;
import com.kdu.smarthome.model.mappers.DtoToEntityMappers;
import com.kdu.smarthome.model.mappers.EntityToDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing inventory-related operations in the Smart Home application.
 */
@Service
@Slf4j
public class InventoryService {
    private InventoryDAO inventoryDAO;
    private DtoToEntityMappers dtoToEntityMappers;
    private EntityToDtoMapper entityToDtoMapper;
    private HouseService houseService;
    private RoomService roomService;

    /**
     * Constructs an InventoryService with the specified InventoryDAO, DtoToEntityMappers,
     * HouseService, RoomService, and EntityToDtoMapper.
     *
     * @param inventoryDAO        The InventoryDAO for accessing inventory data in the database.
     * @param dtoToEntityMappers  The DtoToEntityMappers for converting DTOs to entities.
     * @param houseService        The HouseService for managing house-related operations.
     * @param roomService         The RoomService for managing room-related operations.
     * @param entityToDtoMapper   The EntityToDtoMapper for converting entities to DTOs.
     */
    @Autowired
    public InventoryService(InventoryDAO inventoryDAO,DtoToEntityMappers dtoToEntityMappers,HouseService houseService,RoomService roomService,EntityToDtoMapper entityToDtoMapper){
        this.inventoryDAO=inventoryDAO;
        this.dtoToEntityMappers=dtoToEntityMappers;
        this.houseService=houseService;
        this.roomService=roomService;
        this.entityToDtoMapper=entityToDtoMapper;
    }

    /**
     * Adds a new device based on the provided InventoryRequestDTO.
     *
     * @param inventoryRequestDTO The InventoryRequestDTO containing device details.
     */
    public void addDevice(InventoryRequestDTO inventoryRequestDTO) {
        Inventory inventory=dtoToEntityMappers.convertInventoryRequestDtoToInventory(inventoryRequestDTO);
        inventoryDAO.save(inventory);
        log.info("Device added successfully");
    }

    /**
     * Registers a device based on the provided InventoryRequestDTO.
     *
     * @param inventoryRequestDTO The InventoryRequestDTO containing device details.
     */
    public void registerDevice(InventoryRequestDTO inventoryRequestDTO) {
        Inventory inventory=inventoryDAO.findByKickstonId(inventoryRequestDTO.getKickstonId());
        if(inventory==null){
            throw new InventoryNotFoundException("Device with given Id not found");
        }
        if(!inventory.getDeviceUserName().equals(inventoryRequestDTO.getDeviceUserName())
                && inventory.getDevicePassword().equals(inventoryRequestDTO.getDevicePassword())) {
            throw new DeviceNotFoundWithUserNameException("Username does not match");
        }
        if(!inventory.getDeviceUserName().equals(inventoryRequestDTO.getDeviceUserName())
            || !inventory.getDevicePassword().equals(inventoryRequestDTO.getDevicePassword())) {
            throw new UsernamePasswordInvalidException("Username or Password does not match");
        }
        inventory.setRegistered(true);
        inventoryDAO.save(inventory);
        log.info("Device registered successfully");
    }

    /**
     * Adds a device to the specified room.
     *
     * @param addDeviceToRoomRequestDTO The AddDeviceToRoomRequestDTO containing device and room details.
     */
    public void addDeviceToRoom(AddDeviceToRoomRequestDTO addDeviceToRoomRequestDTO) {
        Inventory inventory=inventoryDAO.findByKickstonId(addDeviceToRoomRequestDTO.getKickstonId());
        if(inventory==null) {
            throw new InventoryNotFoundException("Device with given id not found");
        }
        if(!inventory.getRegistered()) {
            throw new DeviceNotRegisteredException("Device not registered");
        }
        House house=houseService.getHouseById(Integer.parseInt(addDeviceToRoomRequestDTO.getHouseId()));
        if(house==null) {
            throw new HouseNotFoundException("House with given id not found");
        }
        Rooms room=roomService.getRoomById(Integer.parseInt(addDeviceToRoomRequestDTO.getRoomId()));
        if(room==null){
            throw new RoomNotFoundException("Room with given id not found");
        }
        room.addInventory(inventory);
        roomService.saveRoom(room);
        log.info("Device added to room successfully");
    }


    /**
     * Retrieves a list of all devices in the system.
     *
     * @return The list of all devices.
     */
    public List<InventoryResponseObjectDTO> getAllDevices() {
        log.debug("list of devices fetched");
        return entityToDtoMapper.convertFromInventoryToResponseDto(inventoryDAO.findAll());
    }

}
