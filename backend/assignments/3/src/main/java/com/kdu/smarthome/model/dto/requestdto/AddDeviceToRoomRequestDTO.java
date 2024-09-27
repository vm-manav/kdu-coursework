package com.kdu.smarthome.model.dto.requestdto;

import lombok.Data;

@Data
public class AddDeviceToRoomRequestDTO {
    private String houseId;
    private String roomId;
    private String kickstonId;
}
