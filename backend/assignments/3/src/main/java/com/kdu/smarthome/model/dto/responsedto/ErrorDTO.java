package com.kdu.smarthome.model.dto.responsedto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String errorMessage;
    private int statusCode;
}
