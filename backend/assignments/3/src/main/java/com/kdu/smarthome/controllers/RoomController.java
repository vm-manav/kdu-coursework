package com.kdu.smarthome.controllers;

import com.kdu.smarthome.model.dto.requestdto.RoomRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.RoomResponseDTO;
import com.kdu.smarthome.model.mappers.ResponseMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling room-related operations in the Smart Home application.
 * Exposes endpoints for adding rooms to a house.
 */

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private ResponseMappers responseMappers;

    /**
     * Constructor to initialize RoomController with the required ResponseMappers instance.
     *
     * @param responseMappers The ResponseMappers instance for mapping responses.
     */
    @Autowired
    public RoomController(ResponseMappers responseMappers) {
        this.responseMappers=responseMappers;
    }

    /**
     * Endpoint for adding a new room to a house.
     *
     * @param houseId         The ID of the house to which the room will be added.
     * @param roomRequestDTO  The RoomRequestDTO containing room details.
     * @return ResponseEntity containing RoomResponseDTO and HTTP status.
     * @throws NumberFormatException If the provided houseId is not a valid integer.
     */
    @PostMapping("")
    public ResponseEntity<RoomResponseDTO> addRoom(@RequestParam String houseId, @RequestBody RoomRequestDTO roomRequestDTO){
        try{
            Integer.parseInt(houseId);
        } catch (NumberFormatException ex){
            throw new NumberFormatException("House id is invalid");
        }
        return new ResponseEntity<>(responseMappers.addRoomMapper(houseId,roomRequestDTO),HttpStatus.OK);
    }

}
