package com.kdu.smarthome.model.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoomRequestDTO {
    @JsonProperty("room_name")
    private String roomName;
}
