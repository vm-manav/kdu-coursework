package com.kdu.caching.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodeCommonData {
    private double latitude;
    private double longitude;
    private String type;
    private String name;
    private String number;
    private String street;
    private String region;
    private String county;
    private String locality;
    private String neighbourhood;
    private String country;
    private String continent;
    private String label;
}
