package com.kdu.smarthome.model.dto.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HouseRequestDTO {

    @JsonProperty("address")
    private String address;
    @JsonProperty("house_name")
    private String houseName;


    private String username;
}
