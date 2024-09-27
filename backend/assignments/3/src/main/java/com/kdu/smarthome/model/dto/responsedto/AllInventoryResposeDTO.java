package com.kdu.smarthome.model.dto.responsedto;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class AllInventoryResposeDTO {
    private String inventory;
    private HttpStatus httpStatus;
}
