package com.kdu.smarthome.controllers;

import com.kdu.smarthome.model.dto.requestdto.AddDeviceToRoomRequestDTO;
import com.kdu.smarthome.model.dto.requestdto.InventoryRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.AllInventoryResposeDTO;
import com.kdu.smarthome.model.mappers.ResponseMappers;
import com.kdu.smarthome.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling inventory-related operations in the Smart Home application.
 * Exposes endpoints for adding devices, registering devices, and adding devices to rooms.
 */
@RestController
@RequestMapping("/api/v1/")
public class InventoryController {

    private InventoryService inventoryService;
    private ResponseMappers responseMappers;

    /**
     * Constructor to initialize InventoryController with the required services and mappers.
     *
     * @param inventoryService The InventoryService instance for managing inventory-related operations.
     * @param responseMappers  The ResponseMappers instance for mapping responses.
     */
    @Autowired
    public InventoryController(InventoryService inventoryService,ResponseMappers responseMappers) {
        this.inventoryService=inventoryService;
        this.responseMappers=responseMappers;
    }

    /**
     * Endpoint for adding a new device.
     *
     * @param inventoryRequestDTO The InventoryRequestDTO containing device details.
     * @return ResponseEntity containing a success message and HTTP status.
     */
    @PostMapping("inventory")
    public ResponseEntity<String> addDevice(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        inventoryService.addDevice(inventoryRequestDTO);
        return ResponseEntity.ok("Device added successfully");
    }

    /**
     * Endpoint for getting all devices.
     *
     * @return ResponseEntity containing AllInventoryResposeDTO and HTTP status.
     */
    @GetMapping("inventory")
    public ResponseEntity<AllInventoryResposeDTO> getAllDevices() {
        return ResponseEntity.ok(responseMappers.getAllDeviceMapper());
    }
    /**
     * Endpoint for registering a device.
     *
     * @param inventoryRequestDTO The InventoryRequestDTO containing device details.
     * @return ResponseEntity containing a success message and HTTP status.
     */
    @PostMapping("/device/register")
    public ResponseEntity<String> registerDevice(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        inventoryService.registerDevice(inventoryRequestDTO);
        return ResponseEntity.ok("Device registered successfully");
    }

    /**
     * Endpoint for adding a device to a room.
     *
     * @param addDeviceToRoomRequestDTO The AddDeviceToRoomRequestDTO containing device and room details.
     * @return ResponseEntity containing a success message and HTTP status.
     */
    @PostMapping("/device/add")
    public ResponseEntity<String> addDeviceToRoom(@RequestBody AddDeviceToRoomRequestDTO addDeviceToRoomRequestDTO){
        inventoryService.addDeviceToRoom(addDeviceToRoomRequestDTO);
        return ResponseEntity.ok("Device added successfully");
    }
}
