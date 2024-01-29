package com.example.springbootapidemo.dto;

import lombok.Data;

@Data
public class RequestDto {
    private String userName;
    private String password;
    private String email;
}
