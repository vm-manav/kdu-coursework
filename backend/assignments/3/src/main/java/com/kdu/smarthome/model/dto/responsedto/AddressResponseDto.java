package com.kdu.smarthome.model.dto.responsedto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AddressResponseDto {
    private String message;
    private String object;
    private HttpStatus httpStatus;
}
