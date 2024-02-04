package com.kdu.smarthome.model.dto.responsedto;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class RoomAndDeviceResponseDTO {
    private String message;
    private HttpStatus httpStatus;
    private String roomsAndDevices;
}
