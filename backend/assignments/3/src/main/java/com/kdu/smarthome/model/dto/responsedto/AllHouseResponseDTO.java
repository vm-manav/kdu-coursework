package com.kdu.smarthome.model.dto.responsedto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AllHouseResponseDTO {
    private String message;
    private String houses;
    private HttpStatus httpStatus;
}
