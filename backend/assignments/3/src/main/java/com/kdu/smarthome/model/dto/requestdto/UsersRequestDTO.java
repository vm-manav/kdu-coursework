package com.kdu.smarthome.model.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsersRequestDTO {
    private String username;
    private String name;
    private String firstName;
    private String lastName;
    @JsonProperty("password")
    private String password;
    private String emailId;
}
