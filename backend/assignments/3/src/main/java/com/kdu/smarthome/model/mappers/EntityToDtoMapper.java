package com.kdu.smarthome.model.mappers;

import com.kdu.smarthome.model.dto.responsedto.HouseResponseObjectDTO;
import com.kdu.smarthome.model.dto.responsedto.InventoryResponseObjectDTO;
import com.kdu.smarthome.model.dto.responsedto.RoomResponseObjectDTO;
import com.kdu.smarthome.model.entities.House;
import com.kdu.smarthome.model.entities.Inventory;
import com.kdu.smarthome.model.entities.Rooms;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * Component class responsible for mapping entity objects (House, Inventory, Rooms) to corresponding response DTOs.
 * Utilized in various services for converting entity lists to response DTO lists.
 */
@Component
public class EntityToDtoMapper {

    /**
     * Converts a list of House entities to a list of HouseResponseObjectDTOs.
     *
     * @param houses The list of House entities.
     * @return The list of HouseResponseObjectDTOs.
     */
    public List<HouseResponseObjectDTO> convertFromHouseToResponseDto(List<House> houses) {
        List<HouseResponseObjectDTO> houseResponseObjectDTOS=new ArrayList<>();
        for(House house: houses) {
            HouseResponseObjectDTO houseResponseObjectDTO=new HouseResponseObjectDTO(house.getHouseName(),house.getAddress());
            houseResponseObjectDTOS.add(houseResponseObjectDTO);
        }
        return houseResponseObjectDTOS;
    }

    /**
     * Converts a list of Inventory entities to a list of InventoryResponseObjectDTOs.
     *
     * @param inventories The list of Inventory entities.
     * @return The list of InventoryResponseObjectDTOs.
     */
    public List<InventoryResponseObjectDTO> convertFromInventoryToResponseDto(List<Inventory> inventories) {
        List<InventoryResponseObjectDTO> inventoryResponseObjectDTOS=new ArrayList<>();
        for(Inventory inventory: inventories) {
            InventoryResponseObjectDTO inventoryResponseObjectDTO=new InventoryResponseObjectDTO(inventory.getKickstonId(),inventory.getDeviceUserName(),inventory.getDevicePassword());
            inventoryResponseObjectDTOS.add(inventoryResponseObjectDTO);
        }
        return inventoryResponseObjectDTOS;
    }
    /**
     * Converts a list of Rooms entities to a list of RoomResponseObjectDTOs.
     *
     * @param rooms The list of Rooms entities.
     * @return The list of RoomResponseObjectDTOs.
     */
    public List<RoomResponseObjectDTO> convertFromRoomToResposonseDTO(List<Rooms> rooms) {
        List<RoomResponseObjectDTO> roomResponseObjectDTOS=new ArrayList<>();
        for(Rooms room: rooms) {
            RoomResponseObjectDTO roomResponseObjectDTO=new RoomResponseObjectDTO(Integer.toString(room.getId()),room.getRoomName());
            roomResponseObjectDTOS.add(roomResponseObjectDTO);
        }
        return roomResponseObjectDTOS;
    }

}
