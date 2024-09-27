package com.kdu.smarthome.model.mappers;

import com.kdu.smarthome.model.dto.requestdto.HouseRequestDTO;
import com.kdu.smarthome.model.dto.requestdto.RoomRequestDTO;
import com.kdu.smarthome.model.dto.responsedto.*;
import com.kdu.smarthome.model.entities.House;
import com.kdu.smarthome.model.entities.Rooms;
import com.kdu.smarthome.services.HouseService;
import com.kdu.smarthome.services.InventoryService;
import com.kdu.smarthome.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component class for mapping entities and DTOs related to house, room, and inventory responses in the Smart Home application.
 * Utilizes HouseService, InventoryService, and RoomService for data retrieval and manipulation.
 */
@Component
public class ResponseMappers {
    private HouseService houseService;
    private InventoryService inventoryService;
    private RoomService roomService;

    /**
     * Constructs a ResponseMappers with the specified HouseService, InventoryService, and RoomService.
     *
     * @param houseService     The HouseService for accessing house data.
     * @param inventoryService The InventoryService for accessing inventory data.
     * @param roomService      The RoomService for accessing room data.
     */
    public ResponseMappers(HouseService houseService,InventoryService inventoryService,RoomService roomService){
        this.houseService=houseService;
        this.inventoryService=inventoryService;
        this.roomService=roomService;
    }
    /**
     * Maps all houses to a response DTO.
     *
     * @return The response DTO containing a list of houses.
     */
    public AllHouseResponseDTO getALlHousesMapper() {
        List<HouseResponseObjectDTO> list=houseService.getAllHouses();
        AllHouseResponseDTO allHouseResponseDTO=new AllHouseResponseDTO();
        allHouseResponseDTO.setHouses(list.toString());
        allHouseResponseDTO.setMessage("House list fetched");
        allHouseResponseDTO.setHttpStatus(HttpStatus.OK);
        return allHouseResponseDTO;
    }
    /**
     * Maps the update address operation to a response DTO.
     *
     * @param houseId          The ID of the house being updated.
     * @param houseRequestDTO  The DTO containing the updated address details.
     * @return The response DTO indicating the success of the operation.
     */
    public AddressResponseDto  updateAddressMapper(String houseId, HouseRequestDTO houseRequestDTO){
        House house=houseService.updateAddress(houseId,houseRequestDTO);
        AddressResponseDto addressResponseDto=new AddressResponseDto();
        addressResponseDto.setMessage("address updated successfully");
        addressResponseDto.setHttpStatus(HttpStatus.OK);
        addressResponseDto.setObject(house.toString());
        return addressResponseDto;
    }
    /**
     * Maps the create house operation to a response DTO.
     *
     * @param houseRequestDTO  The DTO containing the details for creating a new house.
     * @return The response DTO indicating the success of the operation.
     */
    public HouseResponseDTO createHouseMapper(HouseRequestDTO houseRequestDTO){
        House house=houseService.createHouse(houseRequestDTO);
        HouseResponseDTO houseResponseDTO=new HouseResponseDTO();
        houseResponseDTO.setMessage("House added successfully");
        houseResponseDTO.setHttpStatus(HttpStatus.OK);
        houseResponseDTO.setHouse(new HouseResponseDTO.houseMap(Integer.toString(house.getId())));
        return houseResponseDTO;
    }

    public RoomAndDeviceResponseDTO getAllRoomMapper(String houseId){
        House house=houseService.getHouseById(Integer.parseInt(houseId));
        List<RoomResponseObjectDTO> roomsList=houseService.getListOfRooms(houseId);
        List<InventoryResponseObjectDTO> inventoryList=houseService.getInventory(houseService.getALlRooms(houseId));
        RoomAndDeviceResponseDTO roomAndDeviceResponseDTO=new RoomAndDeviceResponseDTO();
        roomAndDeviceResponseDTO.setRoomsAndDevices(roomsList.toString()+" "+inventoryList.toString()+" "+house.getHouseName()+" "+house.getAddress()+" "+house.getId());
        roomAndDeviceResponseDTO.setMessage("Rooms and inventory fetched");
        roomAndDeviceResponseDTO.setHttpStatus(HttpStatus.OK);
        return roomAndDeviceResponseDTO;
    }
    /**
     * Maps all devices (inventory) to a response DTO.
     *
     * @return The response DTO containing a list of devices.
     */
    public AllInventoryResposeDTO getAllDeviceMapper(){
        AllInventoryResposeDTO allInventoryResposeDTO=new AllInventoryResposeDTO();
        List<InventoryResponseObjectDTO> allDevices= inventoryService.getAllDevices();
        allInventoryResposeDTO.setInventory(allDevices.toString());
        allInventoryResposeDTO.setHttpStatus(HttpStatus.OK);
        return allInventoryResposeDTO;
    }

    /**
     * Maps the add room operation to a response DTO.
     *
     * @param houseId        The ID of the house to which the room is added.
     * @param roomRequestDTO The DTO containing the details for creating a new room.
     * @return The response DTO indicating the success of the operation.
     */
    public RoomResponseDTO addRoomMapper(String houseId, RoomRequestDTO roomRequestDTO){
        Rooms room=roomService.addRoom(houseId,roomRequestDTO);
        RoomResponseDTO roomResponseDTO=new RoomResponseDTO();
        roomResponseDTO.setMessage("room added successfully");
        roomResponseDTO.setHttpStatus(HttpStatus.OK);
        roomResponseDTO.setRoom(new HouseResponseDTO.houseMap(Integer.toString(room.getId())));
        return roomResponseDTO;
    }

}
