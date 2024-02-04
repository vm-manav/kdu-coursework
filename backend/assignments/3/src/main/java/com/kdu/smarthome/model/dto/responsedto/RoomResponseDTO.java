package com.kdu.smarthome.model.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class RoomResponseDTO {
    @Data
    @AllArgsConstructor
    public static class roomMap{
        private String id;
    }
    private String message;
    private HouseResponseDTO.houseMap room;
    private HttpStatus httpStatus;
}
