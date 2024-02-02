package com.example.assesment2.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
        private String userName;
        private String fullName;
        private String emailId;
        private String password;
        private String role;
        private String createdBy;
}
