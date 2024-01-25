package com.example.assignmenet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String errorMessage;
    private int statusCode;
}
