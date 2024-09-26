package com.example.springjpa.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String errorMessage;
    private int statusCode;
}
