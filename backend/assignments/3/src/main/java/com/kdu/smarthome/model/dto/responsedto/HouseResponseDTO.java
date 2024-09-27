package com.kdu.smarthome.model.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HouseResponseDTO {
    @Data
    @AllArgsConstructor
    public static class houseMap{
        private String id;
    }
    private String message;
    private houseMap house;
    private HttpStatus httpStatus;
}
