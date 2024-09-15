package com.kdu.caching.constants;

import lombok.Data;

/**
 * Class containing constant variables used for geocoding and reverse geocoding API URLs.
 * {GEOCODING_URL_LINK}: URL for forward geocoding.
 * {REVERSE_GEOCODING_URL_LINK}: URL for reverse geocoding.
 */


@Data
public class ConstantVariables {
    public static final String GEOCODING_URL_LINK = "http://api.positionstack.com/v1/forward?access_key=";
    public static final String REVERSE_GEOCODING_URL_LINK = "http://api.positionstack.com/v1/reverse?access_key=";

    private ConstantVariables() {
    }
}
