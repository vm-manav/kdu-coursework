package com.kdu.smarthome.controllers;

import com.kdu.smarthome.model.dto.requestdto.HouseRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.*;
import com.kdu.smarthome.model.mappers.ResponseMappers;
import com.kdu.smarthome.services.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling house-related operations in the Smart Home application.
 * Exposes endpoints for creating houses, adding users to houses, retrieving all houses, updating addresses,
 * and getting all rooms in a specific house.
 */

@RestController
@Slf4j
@RequestMapping("/api/v1/house")
public class HouseController {
    private HouseService houseService;
    private ResponseMappers responseMappers;

    /**
     * Constructor to initialize HouseController with the required services and mappers.
     *
     * @param houseService     The HouseService instance for managing house-related operations.
     * @param responseMappers  The ResponseMappers instance for mapping responses.
     */
    @Autowired
    public HouseController(HouseService houseService, ResponseMappers responseMappers) {
        this.houseService=houseService;
        this.responseMappers=responseMappers;
    }
    /**
     * Endpoint for creating a new house.
     *
     * @param houseRequestDTO The HouseRequestDTO containing house details.
     * @return ResponseEntity containing HouseResponseDTO and HTTP status.
     */
    @PostMapping("")
    public ResponseEntity<HouseResponseDTO> createHouse(@RequestBody HouseRequestDTO houseRequestDTO){
        return new ResponseEntity<>(responseMappers.createHouseMapper(houseRequestDTO),HttpStatus.OK);
    }

    /**
     * Endpoint for adding a user to a house.
     *
     * @param houseId          The ID of the house to add the user to.
     * @param houseRequestDTO  The HouseRequestDTO containing user details.
     * @return ResponseEntity containing a success message and HTTP status.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<String> addUserToHouse(@PathVariable String houseId,@RequestBody HouseRequestDTO houseRequestDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String author=authentication.getName();
        houseService.authenticateAdmin(author,Integer.parseInt(houseId));
        houseService.addUserToHouse(houseRequestDTO,houseId);
        return ResponseEntity.ok("user added to house successfully");
    }

    /**
     * Endpoint for retrieving all houses.
     *
     * @return ResponseEntity containing AllHouseResponseDTO and HTTP status.
     */
    @GetMapping("")
    public ResponseEntity<AllHouseResponseDTO> getAllHouses(){
        return ResponseEntity.ok(responseMappers.getALlHousesMapper());
    }

    /**
     * Endpoint for updating the address of a house.
     *
     * @param houseId          The ID of the house to update.
     * @param houseRequestDTO  The HouseRequestDTO containing the updated address.
     * @return ResponseEntity containing AddressResponseDto and HTTP status.
     */
    @PutMapping("")
    public ResponseEntity<AddressResponseDto> updateAddress(@RequestParam String houseId, @RequestBody HouseRequestDTO houseRequestDTO){
        return new ResponseEntity<>(responseMappers.updateAddressMapper(houseId,houseRequestDTO),HttpStatus.OK);
    }

    /**
     * Endpoint for getting all rooms in a specific house.
     *
     * @param houseId The ID of the house to retrieve rooms from.
     * @return ResponseEntity containing RoomAndDeviceResponseDTO and HTTP status.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<RoomAndDeviceResponseDTO> getAllRooms(@PathVariable String houseId){
        return ResponseEntity.ok(responseMappers.getAllRoomMapper(houseId));
    }
}
