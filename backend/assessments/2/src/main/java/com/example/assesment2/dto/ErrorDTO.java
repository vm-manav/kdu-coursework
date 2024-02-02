package com.example.assesment2.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String errorMessage;
    private int statusCode;
}
