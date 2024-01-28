package com.kdu.caching.dto;

import lombok.Data;

@Data
public class ReverseGeoCodeDTO {
    private String name;
    private String type;
    private String street;
    private String region;
    private String locality;
    private String neighbourhood;
    private String country;
    private String continent;
    private String label;
}
