package com.kdu.caching.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Model class representing a list of GeoCodeCommonData objects.
 */
@Data
public class GeoCodeAllDataList {
    @JsonProperty("data")
    private GeoCodeCommonData[] dataArray;
}
