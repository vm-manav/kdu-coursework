package com.kdu.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class representing an error response.
 * {errorMessage}: A String containing the error message.
 * {statusCode}: An integer representing the HTTP status code.
 */

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String errorMessage;
    private int statusCode;
}
