package com.kdu.smarthome.services;

import com.kdu.smarthome.dao.RoomsDAO;
import com.kdu.smarthome.exception.customexceptions.HouseNotFoundException;
import com.kdu.smarthome.model.dto.requestdto.RoomRequestDTO;
import com.kdu.smarthome.model.entities.House;
import com.kdu.smarthome.model.entities.Rooms;
import com.kdu.smarthome.model.mappers.DtoToEntityMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service class for managing room-related operations in the Smart Home application.
 */

@Service
@Slf4j
public class RoomService {
    private RoomsDAO roomsDAO;
    private HouseService houseService;
    private DtoToEntityMappers dtoToEntityMappers;

    /**
     * Constructs a RoomService with the specified RoomsDAO, DtoToEntityMappers, and HouseService.
     *
     * @param roomsDAO           The RoomsDAO for accessing room data in the database.
     * @param dtoToEntityMappers The DtoToEntityMappers for converting DTOs to entities.
     * @param houseService       The HouseService for managing house-related operations.
     */
    @Autowired
    public RoomService(RoomsDAO roomsDAO,DtoToEntityMappers dtoToEntityMappers,HouseService houseService){
        this.roomsDAO=roomsDAO;
        this.dtoToEntityMappers=dtoToEntityMappers;
        this.houseService=houseService;
    }

    /**
     * Adds a new room to the specified house based on the provided RoomRequestDTO.
     *
     * @param houseId        The ID of the house to which the room will be added.
     * @param roomRequestDTO The RoomRequestDTO containing room details.
     * @return The added room entity.
     * @throws HouseNotFoundException If the house with the given ID is not found.
     */
    public Rooms addRoom(String houseId,RoomRequestDTO roomRequestDTO){
        Rooms room=dtoToEntityMappers.convertRoomRequestDtoToRoom(roomRequestDTO);
        roomsDAO.save(room);

        int id=Integer.parseInt(houseId);
        House house=houseService.getHouseById(id);
        if(house==null) {
            throw new HouseNotFoundException("House with given id not found");
        }
        house.addRoom(room);
        houseService.saveHouse(house);
        log.info("room added to house successfully");
        return room;
    }

    /**
     * Retrieves a room based on the provided ID.
     *
     * @param id The ID of the room to retrieve.
     * @return The retrieved room.
     */
    public Rooms getRoomById(int id) {
        log.debug("room with given id fetched");
        return roomsDAO.findById(id);
    }

    /**
     * Saves the provided room entity.
     *
     * @param rooms The room entity to save.
     */
    public void saveRoom(Rooms rooms) {
        log.debug("room saved");
        roomsDAO.save(rooms);
    }
}
